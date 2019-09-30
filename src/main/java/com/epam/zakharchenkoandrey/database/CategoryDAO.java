package com.epam.zakharchenkoandrey.database;

import com.epam.zakharchenkoandrey.entity.Category;
import com.epam.zakharchenkoandrey.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    public static final String SHOW_CATEGORY_LIST_SQL_QUERY = "select * from category where id_user = ?";

    public static final String NAME_COLUMN_ID_IN_DATABASE = "id";
    public static final String NAME_COLUMN_NAME_CATEGORY_IN_DATABASE = "name_category";
    public static final String NAME_COLUMN_ID_USER_IN_DATABASE = "id_user";

    public static final String ADD_CATEGORY_SQL_QUERY = "insert into category (" +
            NAME_COLUMN_NAME_CATEGORY_IN_DATABASE + "," + NAME_COLUMN_ID_USER_IN_DATABASE +
            ") VALUES (?, ?)";

    public static final Logger LOGGER = Logger.getLogger(CategoryDAO.class);

    public List<Category> categoryList (User user) {
        List<Category> categoryList = new ArrayList<Category>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        Connection con = connectionPool.retrieve();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement(SHOW_CATEGORY_LIST_SQL_QUERY);
            stmt.setLong(1, user.getId_user());
            rs = stmt.executeQuery();

            while (rs.next()) {
                Category category = new Category();
                category = setParametersToCategory(category, rs);
                categoryList.add(category);
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                connectionPool.putBack(con);
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }

        return categoryList;
    }

    public void addCategory (Category category) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        Connection con = connectionPool.retrieve();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(ADD_CATEGORY_SQL_QUERY);
            stmt.setString(1, category.getName_category());
            stmt.setLong(2, category.getId_user());
            stmt.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                connectionPool.putBack(con);
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }

    private Category setParametersToCategory(Category category, ResultSet rs) throws SQLException {
        category.setId(rs.getInt(NAME_COLUMN_ID_IN_DATABASE));
        category.setName_category(rs.getString(NAME_COLUMN_NAME_CATEGORY_IN_DATABASE));
        category.setId_user(rs.getInt(NAME_COLUMN_ID_USER_IN_DATABASE));

        return category;
    }
}

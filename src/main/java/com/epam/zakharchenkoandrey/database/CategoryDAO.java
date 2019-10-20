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
        List<Category> categoryList = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        Connection con = connectionPool.retrieve();

        try (PreparedStatement stmt = con.prepareStatement(SHOW_CATEGORY_LIST_SQL_QUERY)){
            stmt.setLong(1, user.getIdUser());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Category category = new Category();
                category = setParametersToCategory(category, rs);
                categoryList.add(category);
            }

        } catch (SQLException e) {
            LOGGER.error("PreparedStatement showListCategory", e);
        } finally {
            connectionPool.putBack(con);
        }

        return categoryList;
    }

    public void addCategory (Category category) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        Connection con = connectionPool.retrieve();

        try (PreparedStatement stmt = con.prepareStatement(ADD_CATEGORY_SQL_QUERY)) {
            stmt.setString(1, category.getNameCategory());
            stmt.setLong(2, category.getIdUser());
            stmt.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            connectionPool.putBack(con);
        }
    }

    private Category setParametersToCategory(Category category, ResultSet rs) throws SQLException {
        category.setId(rs.getInt(NAME_COLUMN_ID_IN_DATABASE));
        category.setNameCategory(rs.getString(NAME_COLUMN_NAME_CATEGORY_IN_DATABASE));
        category.setIdUser(rs.getInt(NAME_COLUMN_ID_USER_IN_DATABASE));

        return category;
    }
}

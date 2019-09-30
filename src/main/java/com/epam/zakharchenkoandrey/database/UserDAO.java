package com.epam.zakharchenkoandrey.database;

import com.epam.zakharchenkoandrey.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;

public class UserDAO {

    public static final String SHOW_USER_BY_EMAIL_SQL_QUERY = "select * from users where e_mail = ?";

    public static final String NAME_COLUMN_ID_IN_DATABASE = "id_user";
    public static final String NAME_COLUMN_EMAIL_IN_DATABASE = "e_mail";
    public static final String NAME_COLUMN_PASSWORD_IN_DATABASE = "password";
    public static final String NAME_COLUMN_NAME_IN_DATABASE = "name";
    public static final String NAME_COLUMN_PREMIUM_IN_DATABASE = "premium";

    public static final Logger LOGGER = Logger.getLogger(UserDAO.class);

    public User getUserByEmail(String userEmail) {

        User user = new User();

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        Connection con = connectionPool.retrieve();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement(SHOW_USER_BY_EMAIL_SQL_QUERY);
            stmt.setString(1, userEmail);
            rs = stmt.executeQuery();

            while (rs.next()) {
                user = setParametersToUser(user, rs);
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

        return user;
    }

    private User setParametersToUser(User user, ResultSet rs) throws SQLException {

        user.setId_user(rs.getLong(NAME_COLUMN_ID_IN_DATABASE));
        user.seteMail(rs.getString(NAME_COLUMN_EMAIL_IN_DATABASE));
        user.setPassword(rs.getString(NAME_COLUMN_PASSWORD_IN_DATABASE));
        user.setName(rs.getString(NAME_COLUMN_NAME_IN_DATABASE));
        user.setPremium(rs.getInt(NAME_COLUMN_PREMIUM_IN_DATABASE));

        return user;
    }
}

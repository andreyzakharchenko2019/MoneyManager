package com.epam.zakharchenkoandrey.database.dao;

import com.epam.zakharchenkoandrey.database.ConnectionPool;
import com.epam.zakharchenkoandrey.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;

public class UserDAO {

    private static final String SHOW_USER_BY_EMAIL_SQL_QUERY = "select * from users where e_mail = ?";

    private static final String NAME_COLUMN_ID_IN_DATABASE = "id_user";
    private static final String NAME_COLUMN_EMAIL_IN_DATABASE = "e_mail";
    private static final String NAME_COLUMN_PASSWORD_IN_DATABASE = "password";
    private static final String NAME_COLUMN_NAME_IN_DATABASE = "name";
    private static final String NAME_COLUMN_PREMIUM_IN_DATABASE = "premium";

    private static final Logger LOGGER = Logger.getLogger(UserDAO.class);

    public User getUserByEmail(String userEmail) {

        User user = new User();

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        Connection con = connectionPool.retrieve();

        try (PreparedStatement stmt = con.prepareStatement(SHOW_USER_BY_EMAIL_SQL_QUERY)) {
            stmt.setString(1, userEmail);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                setParametersToUser(user, rs);
            }

        } catch (SQLException e) {
            LOGGER.error("The exception was occurred when trying to get user", e);
        } finally {
            connectionPool.putBack(con);
        }
        return user;
    }

    private void setParametersToUser(User user, ResultSet rs) throws SQLException {
        user.setIdUser(rs.getLong(NAME_COLUMN_ID_IN_DATABASE));
        user.seteMail(rs.getString(NAME_COLUMN_EMAIL_IN_DATABASE));
        user.setPassword(rs.getString(NAME_COLUMN_PASSWORD_IN_DATABASE));
        user.setName(rs.getString(NAME_COLUMN_NAME_IN_DATABASE));
        user.setPremium(rs.getInt(NAME_COLUMN_PREMIUM_IN_DATABASE));
    }
}

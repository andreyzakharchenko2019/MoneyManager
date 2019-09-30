package com.epam.zakharchenkoandrey.database;

import com.epam.zakharchenkoandrey.entity.Transaction;
import com.epam.zakharchenkoandrey.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    public static final String SHOW_ALL_TRANSACTION_ONE_USER_SQL_QUERY = "select transaction.id, transaction.user_id, " +
            "transaction.date, category.name_category as category, transaction.type_transaction, " +
            "transaction.price, wallet.name_wallet as wallet, transaction.description\n" +
            "from category join transaction on transaction.category = category.id\n" +
            "join wallet on transaction.wallet = wallet.id\n" +
            "\n" +
            "where transaction.user_id = ?";

    public static final String NAME_COLUMN_ID_IN_DATABASE = "id";
    public static final String NAME_COLUMN_USER_ID_IN_DATABASE = "user_id";
    public static final String NAME_COLUMN_DATE_IN_DATABASE = "date";
    public static final String NAME_COLUMN_CATEGORY_IN_DATABASE = "category";
    public static final String NAME_COLUMN_PRICE_IN_DATABASE = "price";
    public static final String NAME_COLUMN_WALLET_IN_DATABASE = "wallet";
    public static final String NAME_COLUMN_DESCRIPTION_IN_DATABASE = "description";
    public static final String NAME_COLUMN_TYPE_TRANSACTION_IN_DATABASE = "type_transaction";

    public static final String ADD_TRANSACTION_SQL_QUERY = "insert into transaction (" +
            NAME_COLUMN_USER_ID_IN_DATABASE + "," + NAME_COLUMN_DATE_IN_DATABASE + "," + NAME_COLUMN_CATEGORY_IN_DATABASE + "," +
            NAME_COLUMN_PRICE_IN_DATABASE + "," + NAME_COLUMN_WALLET_IN_DATABASE + "," +
            NAME_COLUMN_DESCRIPTION_IN_DATABASE + ", " + NAME_COLUMN_TYPE_TRANSACTION_IN_DATABASE + ") VALUES (?, ?, ?, ?, ?, ?, ?)";

    public static final String DELETE_TRANSACTION_SQL_QUERY = "delete from transaction where id = ?";

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public static final Logger LOGGER = Logger.getLogger(TransactionDAO.class);

    public List<Transaction> listTransaction(User user) {
        List<Transaction> transactionList = new ArrayList<Transaction>();

        Connection con = connectionPool.retrieve();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement(SHOW_ALL_TRANSACTION_ONE_USER_SQL_QUERY);
            stmt.setLong(1, user.getId_user());
            rs = stmt.executeQuery();

            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction = setParametersToTransactionForLabel(transaction, rs);
                transactionList.add(transaction);
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

        return transactionList;
    }

    public void addTransaction(Transaction transaction) {

        Connection con = connectionPool.retrieve();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(ADD_TRANSACTION_SQL_QUERY);
            stmt.setLong(1, transaction.getId_user());
            stmt.setString(2, transaction.getDate());
            stmt.setInt(3, transaction.getCategory());
            stmt.setInt(4, transaction.getPrice());
            stmt.setInt(5, transaction.getWallet());
            stmt.setString(6, transaction.getDescription());
            stmt.setInt(7, transaction.getTypeTransaction());
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

    public void deleteTransaction(int id_transaction) {
        Connection con = connectionPool.retrieve();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(DELETE_TRANSACTION_SQL_QUERY);
            stmt.setLong(1, id_transaction);
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

    private Transaction setParametersToTransactionForLabel(Transaction transaction, ResultSet rs) throws SQLException {
        transaction.setId(rs.getLong(NAME_COLUMN_ID_IN_DATABASE));
        transaction.setId_user(rs.getLong(NAME_COLUMN_USER_ID_IN_DATABASE));
        transaction.setDate(rs.getString(NAME_COLUMN_DATE_IN_DATABASE));
        transaction.setCategoryForLabel(rs.getString(NAME_COLUMN_CATEGORY_IN_DATABASE));
        transaction.setPrice(rs.getInt(NAME_COLUMN_PRICE_IN_DATABASE));
        transaction.setWalletForLabel(rs.getString(NAME_COLUMN_WALLET_IN_DATABASE));
        transaction.setDescription(rs.getString(NAME_COLUMN_DESCRIPTION_IN_DATABASE));
        transaction.setTypeTransaction(rs.getInt(NAME_COLUMN_TYPE_TRANSACTION_IN_DATABASE));

        return transaction;
    }
}

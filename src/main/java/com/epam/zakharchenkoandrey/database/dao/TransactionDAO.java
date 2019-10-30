package com.epam.zakharchenkoandrey.database.dao;

import com.epam.zakharchenkoandrey.database.ConnectionPool;
import com.epam.zakharchenkoandrey.entity.Transaction;
import com.epam.zakharchenkoandrey.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    private static final String SHOW_ALL_TRANSACTION_ONE_USER_SQL_QUERY = "select transaction.id, transaction.user_id, " +
            "transaction.date, category.name_category as category, transaction.type_transaction, " +
            "transaction.price, wallet.name_wallet as wallet, transaction.description\n" +
            "from category join transaction on transaction.category = category.id\n" +
            "join wallet on transaction.wallet = wallet.id\n" +
            "\n" +
            "where transaction.user_id = ?";

    private static final String NAME_COLUMN_ID_IN_DATABASE = "id";
    private static final String NAME_COLUMN_USER_ID_IN_DATABASE = "user_id";
    private static final String NAME_COLUMN_DATE_IN_DATABASE = "date";
    private static final String NAME_COLUMN_CATEGORY_IN_DATABASE = "category";
    private static final String NAME_COLUMN_PRICE_IN_DATABASE = "price";
    private static final String NAME_COLUMN_WALLET_IN_DATABASE = "wallet";
    private static final String NAME_COLUMN_DESCRIPTION_IN_DATABASE = "description";
    private static final String NAME_COLUMN_TYPE_TRANSACTION_IN_DATABASE = "type_transaction";

    private static final String ADD_TRANSACTION_SQL_QUERY = "insert into transaction (" +
            NAME_COLUMN_USER_ID_IN_DATABASE + "," + NAME_COLUMN_DATE_IN_DATABASE + "," + NAME_COLUMN_CATEGORY_IN_DATABASE + "," +
            NAME_COLUMN_PRICE_IN_DATABASE + "," + NAME_COLUMN_WALLET_IN_DATABASE + "," +
            NAME_COLUMN_DESCRIPTION_IN_DATABASE + ", " + NAME_COLUMN_TYPE_TRANSACTION_IN_DATABASE + ") VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String DELETE_TRANSACTION_SQL_QUERY = "delete from transaction where id = ?";

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final Logger LOGGER = Logger.getLogger(TransactionDAO.class);

    public List<Transaction> listTransaction(User user) {
        List<Transaction> transactionList = new ArrayList<>();

        Connection con = connectionPool.retrieve();

        try (PreparedStatement stmt = con.prepareStatement(SHOW_ALL_TRANSACTION_ONE_USER_SQL_QUERY)) {
            stmt.setLong(1, user.getIdUser());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Transaction transaction = new Transaction();
                setParametersToTransactionForLabel(transaction, rs);
                transactionList.add(transaction);
            }

        } catch (SQLException e) {
            LOGGER.error("The exception was occurred when trying to get ArrayList with all user's transactions", e);
        } finally {
            connectionPool.putBack(con);
        }

        return transactionList;
    }

    public void addTransaction(Transaction transaction, Connection con) {

        try (PreparedStatement stmt = con.prepareStatement(ADD_TRANSACTION_SQL_QUERY)) {
            stmt.setLong(1, transaction.getIdUser());
            stmt.setString(2, transaction.getDate());
            stmt.setInt(3, transaction.getCategory());
            stmt.setInt(4, transaction.getPrice());
            stmt.setInt(5, transaction.getWallet());
            stmt.setString(6, transaction.getDescription());
            stmt.setInt(7, transaction.getTypeTransaction());
            stmt.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("The exception was occurred when trying to add transaction", e);
        }
    }

    public void deleteTransaction(int idTransaction) {
        Connection con = connectionPool.retrieve();

        try (PreparedStatement stmt = con.prepareStatement(DELETE_TRANSACTION_SQL_QUERY)) {
            stmt.setLong(1, idTransaction);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("The exception was occurred when trying to delete transaction", e);
        } finally {
            connectionPool.putBack(con);
        }
    }

    private void setParametersToTransactionForLabel(Transaction transaction, ResultSet rs) throws SQLException {
        transaction.setId(rs.getLong(NAME_COLUMN_ID_IN_DATABASE));
        transaction.setIdUser(rs.getLong(NAME_COLUMN_USER_ID_IN_DATABASE));
        transaction.setDate(rs.getString(NAME_COLUMN_DATE_IN_DATABASE));
        transaction.setCategoryForLabel(rs.getString(NAME_COLUMN_CATEGORY_IN_DATABASE));
        transaction.setPrice(rs.getInt(NAME_COLUMN_PRICE_IN_DATABASE));
        transaction.setWalletForLabel(rs.getString(NAME_COLUMN_WALLET_IN_DATABASE));
        transaction.setDescription(rs.getString(NAME_COLUMN_DESCRIPTION_IN_DATABASE));
        transaction.setTypeTransaction(rs.getInt(NAME_COLUMN_TYPE_TRANSACTION_IN_DATABASE));
    }
}

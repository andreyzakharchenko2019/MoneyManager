package com.epam.zakharchenkoandrey.database.dao;

import com.epam.zakharchenkoandrey.database.ConnectionPool;
import com.epam.zakharchenkoandrey.entity.Transaction;
import com.epam.zakharchenkoandrey.entity.User;
import com.epam.zakharchenkoandrey.entity.Wallet;
import com.epam.zakharchenkoandrey.exception.AddTransactionException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WalletDAO {

    private static final String SHOW_WALLET_LIST_SQL_QUERY = "select wallet.id, wallet.name_wallet, " +
            "wallet.currency, currency.name_currency as currencyForLabel, wallet.user_id, wallet.amount \n" +
            "from currency join wallet on wallet.currency = currency.id \n" +
            "where wallet.user_id = ?;";

    private static final String NAME_COLUMN_ID_IN_DATABASE = "id";
    private static final String NAME_COLUMN_NAME_WALLET_IN_DATABASE = "name_wallet";
    private static final String NAME_COLUMN_CURRENCY_IN_DATABASE = "currency";
    private static final String NAME_COLUMN_CURRENCY_FOR_LABEL_IN_DATABASE = "currencyForLabel";
    private static final String NAME_COLUMN_USE_ID_IN_DATABASE = "user_id";
    private static final String NAME_COLUMN_AMOUNT_IN_DATABASE = "amount";

    private static final String ADD_WALLET_SQL_QUERY = "insert into wallet (" +
            NAME_COLUMN_NAME_WALLET_IN_DATABASE + "," + NAME_COLUMN_CURRENCY_IN_DATABASE + "," + NAME_COLUMN_USE_ID_IN_DATABASE + "," +
            NAME_COLUMN_AMOUNT_IN_DATABASE + ") VALUES (?, ?, ?, ?)";

    private static final String CHANGE_MINUS_AMOUNT_SQL_QUERY = "UPDATE wallet SET amount = amount - ? where id = ?;";
    private static final String CHANGE_PLUS_AMOUNT_SQL_QUERY = "UPDATE wallet SET amount = amount + ? where id = ?;";

    private static final Logger LOGGER = Logger.getLogger(WalletDAO.class);

    public List<Wallet> walletList (User user) {
        List<Wallet> walletList = new ArrayList<>();

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        Connection con = connectionPool.retrieve();

        try (PreparedStatement stmt = con.prepareStatement(SHOW_WALLET_LIST_SQL_QUERY)) {
            stmt.setLong(1, user.getIdUser());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Wallet wallet = new Wallet();
                setParametersToWallet(wallet, rs);
                walletList.add(wallet);
            }

        } catch (SQLException e) {
            LOGGER.error("The exception was occurred when trying to get ArrayList with user's wallet", e);
        } finally {
            connectionPool.putBack(con);
        }

        return walletList;
    }

    public void addWallet (Wallet wallet) throws AddTransactionException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        Connection con = connectionPool.retrieve();

        try (PreparedStatement stmt = con.prepareStatement(ADD_WALLET_SQL_QUERY)) {
            stmt.setString(1, wallet.getNameWallet());
            stmt.setInt(2, wallet.getCurrency());
            stmt.setLong(3, wallet.getUserId());
            stmt.setInt(4, wallet.getAmount());
            stmt.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("The exception was occurred when trying to add wallet", e);
        } finally {
            connectionPool.putBack(con);
        }
    }

    public void changeAmount (Transaction transaction, Connection con) {
        String preparedStatement;
        if (transaction.getTypeTransaction() == 0) {
            preparedStatement = (CHANGE_MINUS_AMOUNT_SQL_QUERY);
        }
        else {
            preparedStatement = CHANGE_PLUS_AMOUNT_SQL_QUERY;
        }

        try (PreparedStatement stmt = con.prepareStatement(preparedStatement)) {
            stmt.setInt(1, transaction.getPrice());
            stmt.setInt(2, transaction.getWallet());
            stmt.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("The exception was occurred when trying to change wallet's amount", e);
        }
    }

    private void setParametersToWallet(Wallet wallet, ResultSet rs) throws SQLException {
        wallet.setId(rs.getInt(NAME_COLUMN_ID_IN_DATABASE));
        wallet.setNameWallet(rs.getString(NAME_COLUMN_NAME_WALLET_IN_DATABASE));
        wallet.setCurrency(rs.getInt(NAME_COLUMN_CURRENCY_IN_DATABASE));
        wallet.setCurrencyForLabel(rs.getString(NAME_COLUMN_CURRENCY_FOR_LABEL_IN_DATABASE));
        wallet.setUserId(rs.getLong(NAME_COLUMN_USE_ID_IN_DATABASE));
        wallet.setAmount(rs.getInt(NAME_COLUMN_AMOUNT_IN_DATABASE));
    }
}

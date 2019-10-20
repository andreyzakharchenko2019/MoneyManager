package com.epam.zakharchenkoandrey.database;

import com.epam.zakharchenkoandrey.entity.Transaction;
import com.epam.zakharchenkoandrey.entity.User;
import com.epam.zakharchenkoandrey.entity.Wallet;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WalletDAO {

    public static final String SHOW_WALLET_LIST_SQL_QUERY = "select wallet.id, wallet.name_wallet, " +
            "wallet.currency, currency.name_currency as currencyForLabel, wallet.user_id, wallet.amount \n" +
            "from currency join wallet on wallet.currency = currency.id \n" +
            "where wallet.user_id = ?;";

    public static final String NAME_COLUMN_ID_IN_DATABASE = "id";
    public static final String NAME_COLUMN_NAME_WALLET_IN_DATABASE = "name_wallet";
    public static final String NAME_COLUMN_CURRENCY_IN_DATABASE = "currency";
    public static final String NAME_COLUMN_CURRENCY_FOR_LABEL_IN_DATABASE = "currencyForLabel";
    public static final String NAME_COLUMN_USE_ID_IN_DATABASE = "user_id";
    public static final String NAME_COLUMN_AMOUNT_IN_DATABASE = "amount";

    public static final String ADD_WALLET_SQL_QUERY = "insert into wallet (" +
            NAME_COLUMN_NAME_WALLET_IN_DATABASE + "," + NAME_COLUMN_CURRENCY_IN_DATABASE + "," + NAME_COLUMN_USE_ID_IN_DATABASE + "," +
            NAME_COLUMN_AMOUNT_IN_DATABASE + ") VALUES (?, ?, ?, ?)";

    public static final String CHANGE_MINUS_AMOUNT_SQL_QUERY = "UPDATE wallet SET amount = amount - ? where id = ?;";
    public static final String CHANGE_PLUS_AMOUNT_SQL_QUERY = "UPDATE wallet SET amount = amount + ? where id = ?;";

    public static final Logger LOGGER = Logger.getLogger(WalletDAO.class);

    public List<Wallet> walletList (User user) {
        List<Wallet> walletList = new ArrayList<>();

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        Connection con = connectionPool.retrieve();

        try (PreparedStatement stmt = con.prepareStatement(SHOW_WALLET_LIST_SQL_QUERY)) {
            stmt.setLong(1, user.getIdUser());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Wallet wallet = new Wallet();
                wallet = setParametersToWallet(wallet, rs);
                walletList.add(wallet);
            }

        } catch (SQLException e) {
            LOGGER.error("PreparedStatementShowWalletList", e);
        } finally {
            connectionPool.putBack(con);
        }

        return walletList;
    }

    public void addWallet (Wallet wallet) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        Connection con = connectionPool.retrieve();

        try (PreparedStatement stmt = con.prepareStatement(ADD_WALLET_SQL_QUERY)) {
            stmt.setString(1, wallet.getNameWallet());
            stmt.setInt(2, wallet.getCurrency());
            stmt.setLong(3, wallet.getUserId());
            stmt.setInt(4, wallet.getAmount());
            stmt.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("PreparedStatementAddWallet", e);
        } finally {
            connectionPool.putBack(con);
        }
    }

    public void changeAmount (Transaction transaction) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        Connection con = connectionPool.retrieve();

        String preparedStatment;
        if (transaction.getTypeTransaction() == 0) {
            preparedStatment = (CHANGE_MINUS_AMOUNT_SQL_QUERY);
        }
        else {
            preparedStatment = CHANGE_PLUS_AMOUNT_SQL_QUERY;
        }

        try (PreparedStatement stmt = con.prepareStatement(preparedStatment)) {
            stmt.setInt(1, transaction.getPrice());
            stmt.setInt(2, transaction.getWallet());
            stmt.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("PreparedStatementChangeWallet", e);
        } finally {
             connectionPool.putBack(con);
        }
    }

    private Wallet setParametersToWallet(Wallet wallet, ResultSet rs) throws SQLException {
        wallet.setId(rs.getInt(NAME_COLUMN_ID_IN_DATABASE));
        wallet.setNameWallet(rs.getString(NAME_COLUMN_NAME_WALLET_IN_DATABASE));
        wallet.setCurrency(rs.getInt(NAME_COLUMN_CURRENCY_IN_DATABASE));
        wallet.setCurrencyForLabel(rs.getString(NAME_COLUMN_CURRENCY_FOR_LABEL_IN_DATABASE));
        wallet.setUserId(rs.getLong(NAME_COLUMN_USE_ID_IN_DATABASE));
        wallet.setAmount(rs.getInt(NAME_COLUMN_AMOUNT_IN_DATABASE));

        return wallet;
    }
}

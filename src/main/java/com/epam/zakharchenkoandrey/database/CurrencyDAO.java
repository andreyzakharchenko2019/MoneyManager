package com.epam.zakharchenkoandrey.database;

import com.epam.zakharchenkoandrey.entity.Currency;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyDAO {

    public static final String SHOW_CURRENCY_LIST_SQL_QUERY = "select * from currency";

    public static final String NAME_COLUMN_ID_IN_DATABASE = "id";
    public static final String NAME_COLUMN_NAME_CURRENCY_IN_DATABASE = "name_currency";

    public static final Logger LOGGER = Logger.getLogger(CurrencyDAO.class);

    public List<Currency> currencyList () {
        List<Currency> currencyList = new ArrayList<>();

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        Connection con = connectionPool.retrieve();

        try (PreparedStatement stmt = con.prepareStatement(SHOW_CURRENCY_LIST_SQL_QUERY)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Currency currency = new Currency();
                currency = setParametersToCurrency(currency, rs);
                currencyList.add(currency);
            }
        } catch (SQLException e) {
            LOGGER.error("PreparedStatement showListCurrency", e);
        }
        finally {
            connectionPool.putBack(con);
        }

        return currencyList;
    }

    private Currency setParametersToCurrency (Currency currency, ResultSet rs) throws SQLException {
        currency.setId(rs.getInt(NAME_COLUMN_ID_IN_DATABASE));
        currency.setNameCurrency(rs.getString(NAME_COLUMN_NAME_CURRENCY_IN_DATABASE));

        return currency;
    }
}

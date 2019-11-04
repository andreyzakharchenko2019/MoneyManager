package com.epam.zakharchenkoandrey.service;

import com.epam.zakharchenkoandrey.database.ConnectionPool;
import com.epam.zakharchenkoandrey.database.dao.TransactionDAO;
import com.epam.zakharchenkoandrey.database.dao.WalletDAO;
import com.epam.zakharchenkoandrey.entity.Transaction;
import com.epam.zakharchenkoandrey.entity.User;
import com.epam.zakharchenkoandrey.exception.AddTransactionException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TransactionAddService implements Service {

    private static final String AUTHORIZED_USER_ATTRIBUTE = "currentUser";

    private static final String AMOUNT_TRANSACTION_PARAMETER = "amountTransaction";
    private static final String CATEGORY_PARAMETER = "category";
    private static final String TYPE_TRANSACTION_PARAMETER = "typeTransaction";
    private static final String NAME_WALLET_PARAMETER = "nameWallet";
    private static final String DATE_PARAMETER = "dateParam";
    private static final String COMMENT_PARAMETER = "comment";
    private static final String FORMAT_DATE = "yyyy-MM-dd";

    private User user;

    private static final Logger LOGGER = Logger.getLogger(TransactionAddService.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException, AddTransactionException {
        user = (User) request.getSession().getAttribute(AUTHORIZED_USER_ATTRIBUTE);
        Transaction transaction = new Transaction();
        setParametersToTransactionForWriteInDataBase(transaction, request);

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection con = connectionPool.retrieve();


        try {
            con.setAutoCommit(false);
            TransactionDAO transactionDAO = new TransactionDAO();
            transactionDAO.addTransaction(transaction, con);

            WalletDAO walletDAO = new WalletDAO();
            walletDAO.changeAmount(transaction, con);

            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                LOGGER.error("The exception was occurred when trying to rollback transaction - add transaction " +
                        "and change wallet's amount", e);
            }
            LOGGER.error("The exception was occurred when trying to add transaction and change wallet's amount", e);
            throw new AddTransactionException(e);
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                LOGGER.error("The exception was occurred when trying to set auto commit transaction", e);
            }
            connectionPool.putBack(con);
        }

        TransactionService transactionService = new TransactionService();
        transactionService.execute(request, response);
    }

    private void setParametersToTransactionForWriteInDataBase (Transaction transaction, HttpServletRequest request) {
        transaction.setIdUser(user.getIdUser());
        transaction.setPrice(Integer.parseInt(request.getParameter(AMOUNT_TRANSACTION_PARAMETER)));
        transaction.setTypeTransaction(Integer.parseInt(request.getParameter(TYPE_TRANSACTION_PARAMETER)));
        transaction.setCategory(Integer.parseInt(request.getParameter(CATEGORY_PARAMETER)));
        transaction.setWallet(Integer.parseInt(request.getParameter(NAME_WALLET_PARAMETER)));
        if (!request.getParameter(DATE_PARAMETER).equals("")) {
            transaction.setDate(request.getParameter(DATE_PARAMETER));
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
            Calendar calendar = new GregorianCalendar();
            transaction.setDate(dateFormat.format(calendar.getTime()));
        }
        transaction.setDescription(request.getParameter(COMMENT_PARAMETER));
    }
}

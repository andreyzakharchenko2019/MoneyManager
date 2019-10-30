package com.epam.zakharchenkoandrey.service;

import com.epam.zakharchenkoandrey.database.ConnectionPool;
import com.epam.zakharchenkoandrey.database.dao.TransactionDAO;
import com.epam.zakharchenkoandrey.database.dao.WalletDAO;
import com.epam.zakharchenkoandrey.entity.Transaction;
import com.epam.zakharchenkoandrey.entity.User;
import com.epam.zakharchenkoandrey.exception.AddTransactionException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
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

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException, AddTransactionException {
        user = (User) request.getSession().getAttribute(AUTHORIZED_USER_ATTRIBUTE);
        Transaction transaction = new Transaction();
        setParametersToTransactionForWriteInDataBase(transaction, request);

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection con = connectionPool.retrieve();

        try {
            TransactionDAO transactionDAO = new TransactionDAO();
            transactionDAO.addTransaction(transaction, con);

            WalletDAO walletDAO = new WalletDAO();
            walletDAO.changeAmount(transaction, con);

        } catch (Exception e) {
            throw new AddTransactionException(e);
        } finally {
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

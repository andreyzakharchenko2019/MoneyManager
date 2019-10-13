package com.epam.zakharchenkoandrey.service;

import com.epam.zakharchenkoandrey.database.TransactionDAO;
import com.epam.zakharchenkoandrey.database.WalletDAO;
import com.epam.zakharchenkoandrey.entity.Transaction;
import com.epam.zakharchenkoandrey.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TransactionAddService implements Service {

    public static final String AUTHORIZED_USER_ATTRIBUTE = "currentUser";

    public static final String AMOUNT_TRANSACTION_PARAMETER = "amountTransaction";
    public static final String CATEGORY_PARAMETER = "category";
    public static final String TYPE_TRANSACTION_PARAMETER = "typeTransaction";
    public static final String NAME_WALLET_PARAMETER = "nameWallet";
    public static final String DATE_PARAMETER = "dateParam";
    public static final String COMMENT_PARAMETER = "comment";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        User user = (User) request.getSession().getAttribute(AUTHORIZED_USER_ATTRIBUTE);
        Transaction transaction = new Transaction();
        transaction.setIdUser(user.getIdUser());
        transaction.setPrice(Integer.parseInt(request.getParameter(AMOUNT_TRANSACTION_PARAMETER)));
        transaction.setTypeTransaction(Integer.parseInt(request.getParameter(TYPE_TRANSACTION_PARAMETER)));
        transaction.setCategory(Integer.parseInt(request.getParameter(CATEGORY_PARAMETER)));
        transaction.setWallet(Integer.parseInt(request.getParameter(NAME_WALLET_PARAMETER)));
        if (!request.getParameter(DATE_PARAMETER).equals("")) {
            transaction.setDate(request.getParameter(DATE_PARAMETER));
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = new GregorianCalendar();
            transaction.setDate(dateFormat.format(calendar.getTime()));
        }
        transaction.setDescription(request.getParameter(COMMENT_PARAMETER));

        TransactionDAO transactionDAO = new TransactionDAO();
        transactionDAO.addTransaction(transaction);

        WalletDAO walletDAO = new WalletDAO();
        walletDAO.changeAmount(transaction);

        TransactionService transactionService = new TransactionService();
        transactionService.execute(request, response);

    }
}

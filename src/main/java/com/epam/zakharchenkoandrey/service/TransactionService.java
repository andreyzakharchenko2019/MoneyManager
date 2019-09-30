package com.epam.zakharchenkoandrey.service;

import com.epam.zakharchenkoandrey.database.TransactionDAO;
import com.epam.zakharchenkoandrey.entity.Transaction;
import com.epam.zakharchenkoandrey.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TransactionService implements Service {

    public static final String TRANSACTION_LIST_ATTRIBUTE = "transactionAttribute";
    public static final String TRANSACTION_LIST_JSP = "listTransaction.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<Transaction> transactionList;

        TransactionDAO transactionDAO = new TransactionDAO();
        User user = (User) request.getSession().getAttribute("currentUser");
        transactionList = transactionDAO.listTransaction(user);

        request.setAttribute(TRANSACTION_LIST_ATTRIBUTE, transactionList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(TRANSACTION_LIST_JSP);
        requestDispatcher.forward(request, response);
    }
}

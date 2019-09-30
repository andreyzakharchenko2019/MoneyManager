package com.epam.zakharchenkoandrey.service;

import com.epam.zakharchenkoandrey.database.TransactionDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TransactionDeleteService implements Service {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        TransactionDAO transactionDAO = new TransactionDAO();
        transactionDAO.deleteTransaction(Integer.parseInt(request.getParameter("deleteTransaction")));

        TransactionService transactionService = new TransactionService();
        transactionService.execute(request, response);

    }
}

package com.epam.zakharchenkoandrey.service;

import com.epam.zakharchenkoandrey.exception.AddTransactionException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToBackService implements Service {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TransactionService transactionService = new TransactionService();
        transactionService.execute(request, response);
    }
}

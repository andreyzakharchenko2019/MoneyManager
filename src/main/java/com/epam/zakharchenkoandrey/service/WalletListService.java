package com.epam.zakharchenkoandrey.service;

import com.epam.zakharchenkoandrey.database.dao.CurrencyDAO;
import com.epam.zakharchenkoandrey.database.dao.WalletDAO;
import com.epam.zakharchenkoandrey.entity.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class WalletListService implements Service {

    private static final String AUTHORIZED_USER_ATTRIBUTE = "currentUser";
    private static final String AUTHORIZED_LIST_WALLET_ATTRIBUTE = "listWallet";
    private static final String AUTHORIZED_LIST_CURRENCY_ATTRIBUTE = "listCurrency";
    private static final String LIST_WALLET_JSP = "listWallet.jsp";
    private static final String USER_ISN_T_PREMIUM = "userIsnTPremium";
    private static final String USER_ISN_T_PREMIUM_VALUE = "true";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if (request.getAttribute(USER_ISN_T_PREMIUM) != null && request.getAttribute(USER_ISN_T_PREMIUM).equals(USER_ISN_T_PREMIUM_VALUE)) {
            TransactionService transactionService = new TransactionService();
            transactionService.execute(request, response);
        } else {
            WalletDAO walletDAO = new WalletDAO();
            CurrencyDAO currencyDAO = new CurrencyDAO();
            User user = (User) request.getSession().getAttribute(AUTHORIZED_USER_ATTRIBUTE);
            List<Wallet> listWallet = walletDAO.walletList(user);
            List<Currency> listCurrency = currencyDAO.currencyList();
            request.getSession().setAttribute(AUTHORIZED_LIST_WALLET_ATTRIBUTE, listWallet);
            request.getSession().setAttribute(AUTHORIZED_LIST_CURRENCY_ATTRIBUTE, listCurrency);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher(LIST_WALLET_JSP);
            requestDispatcher.forward(request, response);
        }
    }
}

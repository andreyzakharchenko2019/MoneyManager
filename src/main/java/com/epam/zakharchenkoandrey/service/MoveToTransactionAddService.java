package com.epam.zakharchenkoandrey.service;

import com.epam.zakharchenkoandrey.database.dao.CategoryDAO;
import com.epam.zakharchenkoandrey.database.dao.WalletDAO;
import com.epam.zakharchenkoandrey.entity.Category;
import com.epam.zakharchenkoandrey.entity.User;
import com.epam.zakharchenkoandrey.entity.Wallet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MoveToTransactionAddService implements Service {

    private static final String AUTHORIZED_USER_ATTRIBUTE = "currentUser";
    private static final String LIST_WALLET_ATTRIBUTE = "listWallet";
    private static final String LIST_CATEGORY_ATTRIBUTE = "listCategory";
    private static final String TRANSACTION_ADD_JSP = "/WEB-INF/transactionAdd.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        WalletDAO walletDAO = new WalletDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        User user = (User) request.getSession().getAttribute(AUTHORIZED_USER_ATTRIBUTE);
        List<Wallet> listWallet = walletDAO.walletList(user);
        List<Category> listCategory = categoryDAO.categoryList(user);
        request.setAttribute(LIST_WALLET_ATTRIBUTE, listWallet);
        request.setAttribute(LIST_CATEGORY_ATTRIBUTE, listCategory);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(TRANSACTION_ADD_JSP);
        requestDispatcher.forward(request, response);
    }
}

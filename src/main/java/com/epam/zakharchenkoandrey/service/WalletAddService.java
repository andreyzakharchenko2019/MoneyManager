package com.epam.zakharchenkoandrey.service;

import com.epam.zakharchenkoandrey.database.dao.WalletDAO;
import com.epam.zakharchenkoandrey.entity.User;
import com.epam.zakharchenkoandrey.entity.Wallet;
import com.epam.zakharchenkoandrey.exception.AddTransactionException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WalletAddService implements Service {

    private static final String AUTHORIZED_USER_ATTRIBUTE = "currentUser";
    private static final String NAME_WALLET_PARAMETER = "nameWallet";
    private static final String CURRENCY_WALLET_PARAMETER = "currency";
    private static final String AMOUNT_WALLET_PARAMETER = "amountWallet";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AddTransactionException {

        User user = (User) request.getSession().getAttribute(AUTHORIZED_USER_ATTRIBUTE);

        Wallet wallet = new Wallet();
        wallet.setUserId(user.getIdUser());
        wallet.setNameWallet(request.getParameter(NAME_WALLET_PARAMETER));
        wallet.setCurrency(Integer.parseInt(request.getParameter(CURRENCY_WALLET_PARAMETER)));
        wallet.setAmount(Integer.parseInt(request.getParameter(AMOUNT_WALLET_PARAMETER)));

        WalletDAO walletDAO = new WalletDAO();
        walletDAO.addWallet(wallet);

        WalletListService walletListService = new WalletListService();
        walletListService.execute(request, response);

    }
}

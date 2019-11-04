package com.epam.zakharchenkoandrey.service;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {

    private static final String LOG_IN_ACTION = "login";
    private static final String MOVE_TO_TRANSACTION_ADD_ACTION = "moveToTransactionAdd";
    private static final String TRANSACTION_ADD_ACTION = "addTransaction";
    private static final String TRANSACTION_DELETE_ACTION = "deleteTransaction";
    private static final String CHANGE_LANGUAGE_ACTION = "changeLanguage";
    private static final String WALLET_LIST_ACTION = "walletList";
    private static final String ADD_WALLET_ACTION = "addWallet";
    private static final String CATEGORY_LIST_ACTION = "categoryList";
    private static final String ADD_CATEGORY_ACTION = "addCategory";
    private static final String LOG_OUT_ACTION = "logOut";
    private static final String GO_TO_BACK_ACTION = "goToBack";

    private Map<String, Service> serviceMap = new HashMap<>();

    public ServiceFactory() {
        serviceMap.put(LOG_IN_ACTION, new LogInService());
        serviceMap.put(MOVE_TO_TRANSACTION_ADD_ACTION, new MoveToTransactionAddService());
        serviceMap.put(TRANSACTION_ADD_ACTION, new TransactionAddService());
        serviceMap.put(TRANSACTION_DELETE_ACTION, new TransactionDeleteService());
        serviceMap.put(CHANGE_LANGUAGE_ACTION, new ChangeLanguageService());
        serviceMap.put(WALLET_LIST_ACTION, new WalletListService());
        serviceMap.put(ADD_WALLET_ACTION, new WalletAddService());
        serviceMap.put(CATEGORY_LIST_ACTION, new CategoryListService());
        serviceMap.put(ADD_CATEGORY_ACTION, new CategoryAddService());
        serviceMap.put(LOG_OUT_ACTION, new LogOutService());
        serviceMap.put(GO_TO_BACK_ACTION, new GoToBackService());
    }

    public Service getService(String serviceAction) {
        return serviceMap.get(serviceAction);
    }
}

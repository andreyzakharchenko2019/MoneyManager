package com.epam.zakharchenkoandrey.service;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {

    public static final String LOG_IN_ACTION = "login";
    public static final String MOVE_TO_TRANSACTION_ADD_ACTION = "moveToTransactionAdd";
    public static final String TRANSACTION_ADD_ACTION = "addTransaction";
    public static final String TRANSACTION_DELETE_ACTION = "deleteTransaction";
    public static final String CHANGE_LANGUAGE_ACTION = "changeLanguage";
    public static final String WALLET_LIST_ACTION = "walletList";
    public static final String ADD_WALLET_ACTION = "addWallet";
    public static final String CATEGORY_LIST_ACTION = "categoryList";
    public static final String ADD_CATEGORY_ACTION = "addCategory";

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
    }

    public Service getService(String serviceAction) {
        return serviceMap.get(serviceAction);
    }
}

package com.epam.zakharchenkoandrey.exception;

public class AddTransactionException extends Exception {

    private static final String MESSAGE_ADD_TRANSACTION_EXCEPTION =
            "The exception was occurred when trying to add transaction and change wallet's amount";

    public AddTransactionException(Exception e) {
        super(MESSAGE_ADD_TRANSACTION_EXCEPTION, e);
    }
}

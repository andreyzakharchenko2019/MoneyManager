package com.epam.zakharchenkoandrey.entity;

public class Wallet {

    private int id;
    private String nameWallet;
    private int currency;
    private String currencyForLabel;
    private long user_id;
    private int amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameWallet() {
        return nameWallet;
    }

    public void setNameWallet(String nameWallet) {
        this.nameWallet = nameWallet;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrencyForLabel() {
        return currencyForLabel;
    }

    public void setCurrencyForLabel(String currencyForLabel) {
        this.currencyForLabel = currencyForLabel;
    }
}

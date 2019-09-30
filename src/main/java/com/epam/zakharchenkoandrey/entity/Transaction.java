package com.epam.zakharchenkoandrey.entity;

import java.util.Date;

public class Transaction {

    private long id;
    private long id_user;
    private String date;
    private int category;
    private int price;
    private int wallet;
    private String description;
    private String categoryForLabel;
    private String walletForLabel;
    private int typeTransaction;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategoryForLabel(String categoryForLabel) {
        this.categoryForLabel = categoryForLabel;
    }

    public String getWalletForLabel() {
        return walletForLabel;
    }

    public void setWalletForLabel(String walletForLabel) {
        this.walletForLabel = walletForLabel;
    }

    public int getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(int typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public String getCategoryForLabel() {
        return categoryForLabel;
    }
}

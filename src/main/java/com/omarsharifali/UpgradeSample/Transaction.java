package com.omarsharifali.UpgradeSample;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class Transaction {
    private String uid;
    private Timestamp time;
    private String type;
    private double amount;
    private TransactionAccount transferAccount;


    public Transaction(String transactionType, double amount) {  // Make this into an interface
        Date date = new Date();
        this.time = new Timestamp(date.getTime());
        uid = UUID.randomUUID().toString();
        type = transactionType;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public String getUid() {
        return uid;
    }

    public Timestamp getTime() {
        return time;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionAccount getTransferAccount() {
        return transferAccount;
    }

    public void setTransferAccount(TransactionAccount transferAccount) {
        this.transferAccount = transferAccount;
    }

    @Override
    public String toString(){
        return "Transaction ID: " + uid + "  Type: " + type + " Amount: $" + amount+  " at " + time.toString();
    }
}

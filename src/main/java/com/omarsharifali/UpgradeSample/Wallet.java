package com.omarsharifali.UpgradeSample;

import java.util.ArrayList;

public class Wallet {
    private ArrayList<TransactionAccount> accounts = new ArrayList<>();

    public Wallet() {
        accounts.add(new TransactionAccount(0));
    }

    public TransactionAccount getAccount(int index) throws AccountDoesNotExistException {
        if (index < 0 || index >= accounts.size()) {
            throw new AccountDoesNotExistException("Account " + index + " has not been created!");
        }
        return accounts.get(index);
    }

    public int addAccount() {
        accounts.add(new TransactionAccount(0));
        return accounts.size() - 1;
    }
}

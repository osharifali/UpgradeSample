package com.omarsharifali.UpgradeSample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionAccount {
    private double balance;
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public TransactionAccount(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Transaction deposit(double amount) {
        Transaction t = new Transaction("DEPOSIT", amount);
        balance += amount;
        transactions.add(t);
        return t;
    }

    public Transaction withdraw(double amount) throws InvalidBalanceException {
        Transaction t = new Transaction("WITHDRAW", amount);
        if (amount > balance) {
            throw new InvalidBalanceException("You only have "+balance+" dollars to withdraw from this account.");
        } else {
            balance -= amount;
            transactions.add(t);
            return t;
        }
    }
    public Transaction transfer(double amount, TransactionAccount transferAccount) throws InvalidBalanceException {
        Transaction t = new Transaction("TRANSFER", amount);
        t.setTransferAccount(transferAccount);
        transferAccount.deposit(amount);
        withdraw(amount);
        transactions.add(t);
        return t;

    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public String getLastTransactions(int n) {
        int size = transactions.size();
        int lastIndex = size - 1;
        if (n > size) {
            return "There are not enough transactions to return";
        }
        if (n == 1) {
            return transactions.get(lastIndex).toString();
        }
        if (n == size) {
            List<Transaction> copy = transactions;
            Collections.reverse(copy);
            return copy.toString();
        }

        List<Transaction> last = transactions.subList(lastIndex - n+1, size);
        Collections.reverse(last);
        return last.toString();
    }
}

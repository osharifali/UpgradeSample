package com.omarsharifali.UpgradeSample;

import org.springframework.web.bind.annotation.*;


@RestController
public class WalletController {
    Wallet wallet;
    @RequestMapping("/")
    public String welcome() {
        return "Welcome the Upgrade Sample of Omar Sharifali! You can interface with the wallet by following the documentation!";
    }

    @GetMapping("/createWallet")
    public String createWallet(){
        if (wallet != null) {
            return "You have already created a wallet!";
        } else {
            this.wallet = new Wallet();
            return "You have created a new wallet. Your wallet has one account: Account 0";
        }
    }

    @GetMapping("/createAccount")
    public String createAccount() {
        if (wallet == null) {
            return "Wallet Not Found Exception: No wallet created!";
        } else {
            return "New account added to wallet. Account Number: "+wallet.addAccount();
        }
    }

    @GetMapping("/getBalance")
    public String getBalance(@RequestParam(value="account", required=false) String account){
        if (wallet == null) {
            return "Wallet Not Found Exception: No wallet created!";
        } else {
            if (account == null) {
                try {
                    return "Your current balance for Account 0: " + wallet.getAccount(0).getBalance();
                } catch (AccountDoesNotExistException e) {
                    return e.getMessage();
                }
            } else {
                int accountNum = Integer.parseInt(account);
                try {
                    return "Your current balance for Account "+accountNum+" is: " + wallet.getAccount(accountNum).getBalance();
                } catch (AccountDoesNotExistException e) {
                    return e.getMessage();
                }
            }
        }
    }

    @GetMapping("/withdraw")
    public String withdraw(@RequestParam("amount") double amount,@RequestParam(value="account", required=false) String account)  {
        if (wallet == null) {
            return "Wallet Not Found Exception: No wallet created!";
        } else {
            try {
                if (account == null) {
                    try {
                        wallet.getAccount(0).withdraw(amount);
                        return "Withdrawal from Account 0 successful, new balance: " + wallet.getAccount(0).getBalance();
                    } catch (AccountDoesNotExistException e) {
                        return e.getMessage();
                    }

                } else {
                    int accountNum = Integer.parseInt(account);
                    try {
                        wallet.getAccount(accountNum).withdraw(amount);
                        return "Withdrawal from Account "+accountNum+" successful, new balance: " + wallet.getAccount(accountNum).getBalance();
                    } catch (AccountDoesNotExistException e) {
                        return e.getMessage();
                    }

                }
            } catch (InvalidBalanceException e) {
                return e.getMessage();
            }
        }
    }

    @GetMapping("/deposit")
    public String deposit(@RequestParam("amount") double amount,@RequestParam(value="account", required=false) String account) {
        if (wallet == null) {
            return "Wallet Not Found Exception: No wallet created!";
        } else {
            if (account == null) {
                try {
                    wallet.getAccount(0).deposit(amount);
                    return "Deposit to Account 0 successful, new balance: " + wallet.getAccount(0).getBalance();
                } catch (AccountDoesNotExistException e) {
                    return e.getMessage();
                }

            } else {
                int accountNum = Integer.parseInt(account);
                try {
                    wallet.getAccount(accountNum).deposit(amount);
                    return "Deposit to Account "+accountNum+" successful, new balance: " + wallet.getAccount(accountNum).getBalance();
                } catch (AccountDoesNotExistException e) {
                    return e.getMessage();
                }

            }

        }
    }

    @GetMapping("/transfer")
    public String transfer(@RequestParam("amount") double transferAmount, @RequestParam(value="from", required = false) String from,
                           @RequestParam("to") String to)  {
        int toAccount = Integer.parseInt(to);
        if (wallet == null) {
            return "Wallet Not Found Exception: No wallet created!";
        } else {

            try {
                if (from == null) {
                    try {
                        TransactionAccount account2 = wallet.getAccount(toAccount);
                        wallet.getAccount(0).transfer(transferAmount, account2);
                        return "You have successfully transferred from Account 0 to Account #" + toAccount + ", it's new balance is " + account2.getBalance();
                    } catch (AccountDoesNotExistException e) {
                        return e.getMessage();
                    }

                } else {
                    int fromAccount = Integer.parseInt(from);
                    try {
                        TransactionAccount account2 = wallet.getAccount(toAccount);
                        wallet.getAccount(fromAccount).transfer(transferAmount, account2);
                        return "You have successfully transferred from Account "+fromAccount+" to Account #" + toAccount + ", it's new balance is " + account2.getBalance();
                    } catch (AccountDoesNotExistException e) {
                        return e.getMessage();
                    }
                }
            } catch (InvalidBalanceException e) {
                return e.getMessage();
            }

        }
    }

    @GetMapping("/getTransactions")
    public String getTransactions(@RequestParam("number") int number) {
        if (wallet == null) {
            return "Wallet Not Found Exception: No wallet created!";
        } else {
            TransactionAccount account0 = null;
            try {
                account0 = wallet.getAccount(0);
                return account0.getLastTransactions(number);
            } catch (AccountDoesNotExistException e) {
                return e.getMessage();
            }
        }
    }
}

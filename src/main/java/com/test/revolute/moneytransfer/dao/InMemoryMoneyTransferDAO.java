package com.test.revolute.moneytransfer.dao;

import com.test.revolute.moneytransfer.model.Account;
import com.test.revolute.moneytransfer.model.Transaction;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryMoneyTransferDAO {

    ConcurrentHashMap<Long, Account> dataStore;
    private final static int ROUND_MODE = BigDecimal.ROUND_CEILING;
    private final static int SCALE = 4;

    public InMemoryMoneyTransferDAO() {
        dataStore = new ConcurrentHashMap<>();
        createTestData();
    }

    public void transferFunds(Transaction transaction) {
        Account fromAccount = dataStore.get(transaction.getFromAccountId());
        Account toAccount = dataStore.get(transaction.getToAccountId());
            synchronized (this) {
                if(fromAccount.getBalance().compareTo(transaction.getAmount()) > 0){
                    fromAccount.setBalance(fromAccount.getBalance().subtract(transaction.getAmount()).setScale(SCALE,ROUND_MODE));
                    toAccount.setBalance(toAccount.getBalance().add(transaction.getAmount()).setScale(SCALE,ROUND_MODE));
                }
                else
                    throw new IllegalArgumentException("Not enough balance in the account!");
        }
    }

    public boolean isValidAccount(long accountId) {
        return dataStore.containsKey(accountId);
    }

    private void addAccountToDataStore(Account account) {
        dataStore.putIfAbsent(account.getAccountId(),account);
    }

    private void createTestData() {
        Account accountJson = new Account(123,"Json", new BigDecimal(1200.000).setScale(SCALE,ROUND_MODE),"GBP");
        Account accountRoy = new Account(124,"Roy", new BigDecimal(1500.000).setScale(SCALE,ROUND_MODE),"GBP");
        Account accountStephanie = new Account(125,"Stephanie", new BigDecimal(900.000).setScale(SCALE,ROUND_MODE),"GBP");
        Account accountSander = new Account(126,"Sander", new BigDecimal(2800.000).setScale(SCALE,ROUND_MODE),"GBP");
        Account accountEd = new Account(127,"Ed", new BigDecimal(2000.000).setScale(SCALE,ROUND_MODE),"GBP");
        addAccountToDataStore(accountJson);
        addAccountToDataStore(accountEd);
        addAccountToDataStore(accountRoy);
        addAccountToDataStore(accountSander);
        addAccountToDataStore(accountStephanie);
    }


}

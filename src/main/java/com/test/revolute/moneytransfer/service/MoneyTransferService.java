package com.test.revolute.moneytransfer.service;

import com.test.revolute.moneytransfer.dao.InMemoryMoneyTransferDAO;
import com.test.revolute.moneytransfer.model.Transaction;


public class MoneyTransferService{

    private InMemoryMoneyTransferDAO moneyTransferDAO;

    public MoneyTransferService(){
        moneyTransferDAO = new InMemoryMoneyTransferDAO();
    }

    public void transferFunds(Transaction transaction) {
        if (! moneyTransferDAO.isValidAccount(transaction.getFromAccountId())) {
            throw new IllegalArgumentException("Invalid From Account ID - Please check the account ID "+ transaction.getFromAccountId());
        }
        if (! moneyTransferDAO.isValidAccount(transaction.getToAccountId())) {
            throw new IllegalArgumentException("Invalid To Account ID - Please check the account ID "+ transaction.getToAccountId());
        }
        moneyTransferDAO.transferFunds(transaction);
    }
}

package com.test.revolute.moneytransfer.dao;

import com.test.revolute.moneytransfer.model.Account;
import com.test.revolute.moneytransfer.model.Transaction;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import static org.junit.Assert.*;

public class InMemoryMoneyTransferDAOTest {

    static InMemoryMoneyTransferDAO inMemoryMoneyTransferDAO;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void setup(){
        inMemoryMoneyTransferDAO = new InMemoryMoneyTransferDAO();

    }

    @Test
    public void testTransferFunds_SuccessScenario() {
        BigDecimal amount = new BigDecimal(10).setScale(4, RoundingMode.HALF_EVEN);
        Transaction transaction = new Transaction(amount,123l,124l);

        inMemoryMoneyTransferDAO.transferFunds(transaction);
        Account fromAccount = inMemoryMoneyTransferDAO.dataStore.get(123l);
        Account toAccount = inMemoryMoneyTransferDAO.dataStore.get(124l);
        assertEquals(fromAccount.getBalance(),new BigDecimal(1190).setScale(4,BigDecimal.ROUND_CEILING));
        assertEquals(toAccount.getBalance(),new BigDecimal(1510).setScale(4,BigDecimal.ROUND_CEILING));
    }

    @Test
    public void testIfAccountIdIsValid_SuccessScenario() {
        assertTrue(inMemoryMoneyTransferDAO.isValidAccount(123l));
    }

    @Test
    public void testNotEnoughFundsInAccount_shoudlThrow() {
        thrown.expect(IllegalArgumentException.class);
        BigDecimal amount = new BigDecimal(1200).setScale(4, RoundingMode.HALF_EVEN);
        Transaction transaction = new Transaction(amount,123l,124l);

        inMemoryMoneyTransferDAO.transferFunds(transaction);
    }
}

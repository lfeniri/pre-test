package com.priceminister.account;


import com.priceminister.account.implementation.CustomerAccount;
import com.priceminister.account.implementation.CustomerAccountRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;


/**
 * Please create the business code, starting from the unit tests below.
 * Implement the first test, the develop the code that makes it pass.
 * Then focus on the second test, and so on.
 * 
 * We want to see how you "think code", and how you organize and structure a simple application.
 * 
 * When you are done, please zip the whole project (incl. source-code) and send it to recrutement-dev@priceminister.com
 * 
 */
public class CustomerAccountTest {
    
    private Account customerAccount;
    private AccountRule rule;
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        customerAccount = new CustomerAccount();
    }

    /**
     * Tests that an empty account always has a balance of 0.0, not a NULL.
     */
    @Test
    public void testAccountWithoutMoneyHasZeroBalance() {
        assertEquals(new Double(0),customerAccount.getBalance());
    }

    /**
     * Adds money to the account and checks that the new balance is as expected.
     */
    @Test
    public void testAddPositiveAmount() throws TechnicalException {
        Double expected = 20d;
        customerAccount.add(20d);
        assertEquals(expected,customerAccount.getBalance());
    }

    /**
     * Adds money to the account and checks that the new balance is as expected.
     */
    @Test
    public void testAddNegativeAmount() throws TechnicalException {
        thrown.expect(TechnicalException.class);
        customerAccount.add(-20d);
    }
    /**
     * Tests WithdrawAndReportBalance when the balance withdrawn
     */
    @Test
    public void testWithdrawAndReportBalance_OK_case() throws IllegalBalanceException, TechnicalException {
        Double expected = 20d;
        rule = new CustomerAccountRule();
        customerAccount.add(30d);
        customerAccount.withdrawAndReportBalance(10d,rule);
        assertEquals(expected, customerAccount.getBalance());
    }

    /**
     * Tests that an illegal withdrawal throws the expected exception.
     * Use the logic contained in CustomerAccountRule; feel free to refactor the existing code.
     */
    @Test
    public void testWithdrawAndReportBalanceIllegalBalance() throws IllegalBalanceException, TechnicalException {
        thrown.expect(IllegalBalanceException.class);
        rule = new CustomerAccountRule();
        customerAccount.withdrawAndReportBalance(20d,rule);
    }

    /**
     * Tests the message of the exception IllegalBalance.
     */
    @Test
    public void testWithdrawAndReportBalanceIllegalBalance_and_check_the_exception_message() throws TechnicalException {
        rule = new CustomerAccountRule();
        try{
            customerAccount.withdrawAndReportBalance(20d,rule);
        }catch (IllegalBalanceException e){
            assertEquals("Illegal account balance: -20.0",e.toString());
        }
    }

    /**
     * Tests that when a null argument, the method throw a TechnicaalException.
     */
    @Test
    public void testWithdrawAndReportBalance_need_to_throw_TechnicalException_when_argument_null() throws IllegalBalanceException, TechnicalException {
        thrown.expect(TechnicalException.class);
        customerAccount.withdrawAndReportBalance(20d,null);
    }

    /**
     * Tests that when a withdrawnAmount argument have a negative value, the method throw a TechnicaalException.
     */
    @Test
    public void testWithdrawAndReportBalance_need_to_throw_TechnicalException_when_withdrawnAmount_is_negatif() throws IllegalBalanceException, TechnicalException {
        thrown.expect(TechnicalException.class);
        rule = new CustomerAccountRule();
        customerAccount.withdrawAndReportBalance(-20d,rule);
    }
    
    // Also implement missing unit tests for the above functionalities.

}

package com.priceminister.account.implementation;

import com.priceminister.account.*;


public class CustomerAccount implements Account {

    private Double balance = (double) 0;

    public void add(Double addedAmount) throws TechnicalException {
        if(addedAmount == null || addedAmount < 0) {
            throw new TechnicalException("The amount need to be a positive value");
        }
        balance += addedAmount;
    }

    public Double getBalance() {
        return balance;
    }

    public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule)
            throws IllegalBalanceException, TechnicalException {
        if(withdrawnAmount == null || rule == null){
            throw new TechnicalException("One of withdrawAndReportBalance arguments is not initialized");
        }
        if(withdrawnAmount < 0){
            throw new TechnicalException("withdrawnAmount in withdrawAndReportBalance need to be a positive value");
        }
        double balanceAfterWithdraw = balance - withdrawnAmount;
        if(!rule.withdrawPermitted(balanceAfterWithdraw)) {
            throw new IllegalBalanceException(balanceAfterWithdraw);
        }
        balance = balanceAfterWithdraw;
        return balance;
    }

}

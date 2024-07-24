package com.bank.of.pac.service;

import com.bank.of.pac.model.MstTransaction;
import com.bank.of.pac.model.MstUser;

import java.util.List;

public interface TransactionService {
    public MstUser getBalance(Long userId);

    public String transfer(Long fromAccount, Long toAccount, double amount, String type);

    public MstTransaction getLastTransaction();

    public List<MstTransaction> getTransactionHistory(Long accountId);
}

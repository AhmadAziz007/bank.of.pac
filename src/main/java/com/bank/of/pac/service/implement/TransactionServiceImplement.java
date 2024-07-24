package com.bank.of.pac.service.implement;

import com.bank.of.pac.model.MstTransaction;
import com.bank.of.pac.model.MstUser;
import com.bank.of.pac.repository.TransactionRepository;
import com.bank.of.pac.repository.UserRepository;
import com.bank.of.pac.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Service(value = "bankService")
public class TransactionServiceImplement implements TransactionService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TransactionRepository transactionRepo;

    private MstTransaction lastTransaction;

    @Override
    public MstUser getBalance(Long userId) {
        return userRepo.findByUserId(userId).orElse(null);
    }

    @Transactional
    @Override
    public String transfer(Long fromAccount, Long toAccount, double amount, String type) {
        log.info("Inside Transfer{}");
        MstUser fromUser = userRepo.findByUserId(fromAccount).orElse(null);
        MstUser toUser = userRepo.findByUserId(toAccount).orElse(null);

        if (fromUser == null || toUser == null) {
            return "User not found";
        }

        if (amount< 20000) {
            return "Amount must be greater than 20000";
        }

        if (!type.equalsIgnoreCase("debit") && !type.equalsIgnoreCase("credit")) {
            return "Input you transaction type";
        }

        fromUser.setBalance(fromUser.getBalance() - amount);
        toUser.setBalance(toUser.getBalance() + amount);

        userRepo.save(fromUser);
        userRepo.save(toUser);

        MstTransaction transaction = new MstTransaction();
        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);
        transaction.setAmount(amount);
        transaction.setDate(new Date());
        transaction.setType(type);

        lastTransaction = transactionRepo.save(transaction);

        return "Transfer successful";
    }

    @Override
    public MstTransaction getLastTransaction() {
        return lastTransaction;
    }

    @Override
    public List<MstTransaction> getTransactionHistory(Long accountId) {
        return transactionRepo.findByFromAccountOrToAccount(accountId, accountId);
    }
}

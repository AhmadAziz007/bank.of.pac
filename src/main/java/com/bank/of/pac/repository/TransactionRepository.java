package com.bank.of.pac.repository;

import com.bank.of.pac.model.MstTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<MstTransaction, Long> {
    List<MstTransaction> findByFromAccountOrToAccount(Long fromAccount, Long toAccount);

}

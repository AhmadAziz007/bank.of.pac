package com.bank.of.pac.common;

import com.bank.of.pac.model.MstTransaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TransactionHistoryResponse {
    private Long userId;
    private List<MstTransaction> transactions;
}

package com.bank.of.pac.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferRequest {
    private Long fromAccount;
    private Long toAccount;
    private double amount;
}

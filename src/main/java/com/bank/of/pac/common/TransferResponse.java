package com.bank.of.pac.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransferResponse {
    private String status;
    private Long transactionId;
    private String message;
}

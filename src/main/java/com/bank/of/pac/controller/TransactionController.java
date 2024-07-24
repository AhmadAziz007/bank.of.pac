package com.bank.of.pac.controller;

import com.bank.of.pac.model.MstTransaction;
import com.bank.of.pac.model.MstUser;
import com.bank.of.pac.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/balance")
    public ResponseEntity<?> getBalance(@RequestBody Map<String, Object> request) {
        Long userId = ((Number) request.get("user_id")).longValue();
        MstUser user = transactionService.getBalance(userId);
        if (user == null) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "User not found");
            return ResponseEntity.status(404).body(errorResponse);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("user_id", user.getUserId());
        response.put("balance", user.getBalance());
        return ResponseEntity.ok(response);
    }


    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody Map<String, Object> transferRequest){
        Long fromAccount = ((Number) transferRequest.get("from_account")).longValue();
        Long toAccount = ((Number) transferRequest.get("to_account")).longValue();
        double amount = ((Number) transferRequest.get("amount")).doubleValue();
        String type = (String) transferRequest.get("type");

        if (type == null || type.trim().isEmpty()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "failed");
            errorResponse.put("message", "Invalid transaction type");
            return ResponseEntity.status(400).body(errorResponse);
        }

        String result = transactionService.transfer(fromAccount, toAccount, amount, type);
        if (result.equals("Transfer successful")) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("transaction_id", transactionService.getLastTransaction().getTransactionId());
            response.put("message", "result");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "failed");
            errorResponse.put("message", result);
            return ResponseEntity.status(400).body(errorResponse);
        }
    }

    @PostMapping("/transactions")
    public ResponseEntity<?> getTransactionHistory(@RequestBody Map<String, Object> request) {
        Long accountId = ((Number) request.get("account_id")).longValue();
        List<MstTransaction> transactions = transactionService.getTransactionHistory(accountId);
        MstUser user = transactionService.getBalance(accountId);

        Map<String, Object> response = new HashMap<>();
        response.put("account_id", accountId);
        response.put("transactions", transactions);
        response.put("balance", user.getBalance());
        return ResponseEntity.ok(response);
    }
}

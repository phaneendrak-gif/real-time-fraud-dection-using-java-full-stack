
package com.example.fraud.controller;

import com.example.fraud.model.Transaction;
import com.example.fraud.service.FraudDetectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TransactionController {

    private final FraudDetectionService fraudDetectionService;

    public TransactionController(
            FraudDetectionService fraudDetectionService) {
        this.fraudDetectionService = fraudDetectionService;
    }

    @PostMapping("/transactions")
    public ResponseEntity<Transaction> submitTransaction(
            @RequestBody Transaction transaction) {

        Transaction submitted =
                fraudDetectionService.processTransaction(transaction);

        return ResponseEntity.accepted().body(submitted);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getTransactions() {
        return ResponseEntity.ok(
                fraudDetectionService.getAllTransactions());
    }

    @GetMapping("/alerts")
    public ResponseEntity<List<Transaction>> getFraudAlerts() {
        return ResponseEntity.ok(
                fraudDetectionService.getFraudAlerts());
    }
}


package com.example.fraud.service;

import com.example.fraud.model.Transaction;
import com.example.fraud.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FraudDetectionService {

    private final TransactionRepository transactionRepository;

    public FraudDetectionService(
            TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction processTransaction(Transaction transaction) {

        boolean suspicious = false;

        if (transaction.getAmount() >= 10000) {
            suspicious = true;
        }

        if ("XX".equalsIgnoreCase(transaction.getCountry())) {
            suspicious = true;
        }

        transaction.setFraudulent(suspicious);

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getFraudAlerts() {
        return transactionRepository.findByFraudulentTrue();
    }
}

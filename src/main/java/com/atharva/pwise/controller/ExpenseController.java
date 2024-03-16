package com.atharva.pwise.controller;

import com.atharva.pwise.entity.Transaction;
import com.atharva.pwise.entity.payload.ExpensePayload;
import com.atharva.pwise.service.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseController.class);
    @Autowired
    ExpenseService expenseService;

    @GetMapping("/")
    public ResponseEntity<List<Transaction>> getAllExpenses(@RequestParam Long userId) {
        return new ResponseEntity<>(expenseService.getAllExpenses(userId), HttpStatus.OK);
    }
    @GetMapping("/id")
    public ResponseEntity<Transaction> getExpenseById(@RequestParam Long transactionId) {
        return new ResponseEntity<>(expenseService.getTransactionById(transactionId), HttpStatus.OK);
    }
    @GetMapping("/betweenDates")
    public ResponseEntity<List<Transaction>> getExpensePerMonth(@RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate,
                                                                @RequestParam Long userId) {
        return new ResponseEntity<>(expenseService.getTransactionsBetweenDates(fromDate, toDate, userId), HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<Transaction> createTransaction(@RequestBody ExpensePayload expensePayload, @RequestParam Long userId) {
        return new ResponseEntity<>(expenseService.insertExpense(expensePayload, userId), HttpStatus.CREATED);
    }
    @DeleteMapping("/")
    public void deleteExpenseById(@RequestParam Long transactionId) {
        expenseService.deleteTransactionById(transactionId);
    }

}

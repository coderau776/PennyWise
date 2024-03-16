package com.atharva.pwise.controller;

import com.atharva.pwise.entity.Transaction;
import com.atharva.pwise.entity.response.ExpenseSummary;
import com.atharva.pwise.service.ExpenseAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/analysis")
public class ExpenseAnalysisController {
    @Autowired
    ExpenseAnalysisService expenseAnalysisService;
    @GetMapping("/summary")
    public ResponseEntity<ExpenseSummary> getExpenseSummary(@RequestParam Long userId) {
        return new ResponseEntity<>(expenseAnalysisService.getSummaryForUser(userId), HttpStatus.OK);
    }
    @GetMapping("/byCategory")
    public ResponseEntity<List<Transaction>> getExpensesByCategory(@RequestParam String categoryName, @RequestParam Long userId) {
        return new ResponseEntity<>(expenseAnalysisService.getExpensesByCategory(categoryName, userId), HttpStatus.OK);
    }
    @GetMapping("/perCategory")
    public ResponseEntity<Map<String, List<Transaction>>> getAllExpensesPerCategory(@RequestParam Long userId) {
        return new ResponseEntity<>(expenseAnalysisService.getAllExpensesPerCategory(userId), HttpStatus.OK);
    }
}

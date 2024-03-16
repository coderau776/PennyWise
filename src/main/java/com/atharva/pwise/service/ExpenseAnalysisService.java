package com.atharva.pwise.service;

import com.atharva.pwise.entity.ExpenseCategory;
import com.atharva.pwise.entity.Transaction;
import com.atharva.pwise.entity.User;
import com.atharva.pwise.entity.response.ExpenseSummary;
import com.atharva.pwise.exception.ResourceNotFoundException;
import com.atharva.pwise.repository.CategoryRepository;
import com.atharva.pwise.repository.ExpenseRepository;
import com.atharva.pwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.atharva.pwise.utility.Constants.*;
import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class ExpenseAnalysisService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ExpenseRepository expenseRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ExpenseService expenseService;
    public ExpenseSummary getSummaryForUser(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(USER, USER_ID, userId));
        long budget = user.getBudget();
        long totalExpense = expenseService.getTransactionsBetweenDates(LocalDateTime.now().withDayOfMonth(1), LocalDateTime.now(), userId)
                .stream().filter(Transaction::isExpense).map(Transaction::getAmount).reduce(0L, Long::sum);
        long safeToSpend = calculateSafeToSpend(budget, totalExpense);
        return ExpenseSummary.builder().totalExpense(totalExpense).budget(budget).safeToSpendPerDay(safeToSpend).build();
    }
    private long calculateSafeToSpend(long budget, long totalExpense) {
        LocalDate today = LocalDate.now();
        long daysRemaining = DAYS.between(today, today.withDayOfMonth(today.lengthOfMonth()));
        return (budget - totalExpense) / daysRemaining;
    }
    public List<Transaction> getExpensesByCategory(String categoryName, Long userId) {
        ExpenseCategory expenseCategory = categoryRepository.findByCategoryNameAndUserUserId(categoryName, userId).orElseThrow(() -> new ResourceNotFoundException(EXPENSE_CATEGORY, CATEGORY_ID, List.of(categoryName, userId)));
        return expenseRepository.getTransactionByCategoryId(expenseCategory.getId());
    }

    public Map<String, List<Transaction>> getAllExpensesPerCategory(Long userId) {
        List<Transaction> allTransactions = expenseRepository.getTransactionByUserUserId(userId).orElseThrow(() -> new ResourceNotFoundException(TRANSACTION, USER_ID, userId));
        return allTransactions.stream().collect(Collectors.groupingBy(transaction -> transaction.getCategory().getCategoryName()));
    }
}

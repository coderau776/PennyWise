package com.atharva.pwise.service;

import com.atharva.pwise.utility.Utility;
import com.atharva.pwise.entity.BankAccountInfo;
import com.atharva.pwise.entity.ExpenseCategory;
import com.atharva.pwise.entity.Transaction;
import com.atharva.pwise.entity.User;
import com.atharva.pwise.entity.payload.ExpensePayload;
import com.atharva.pwise.exception.ResourceNotFoundException;
import com.atharva.pwise.repository.BankAccountRepository;
import com.atharva.pwise.repository.CategoryRepository;
import com.atharva.pwise.repository.ExpenseRepository;
import com.atharva.pwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static com.atharva.pwise.utility.Constants.*;

@Service
public class ExpenseService {
    @Autowired
    ExpenseRepository expenseRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BankAccountRepository bankAccountRepository;

    public Transaction insertExpense(ExpensePayload expensePayload, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(USER, USER_ID, userId));
        BankAccountInfo bankAccountInfo = bankAccountRepository.findByBankAccNumberAndUserUserId(expensePayload.getBankAccNumber(), userId).orElseThrow(() -> new ResourceNotFoundException("bank account", "number & userId", expensePayload.getBankAccNumber() + " & " + userId));
        ExpenseCategory expenseCategory = categoryRepository.findByCategoryNameAndUserUserId(expensePayload.getCategory(), userId).orElseThrow(() -> new ResourceNotFoundException("expense category", "category & userId", expensePayload.getCategory() + " & " + userId));
        Transaction transaction = getTransactionEntity(expensePayload, bankAccountInfo, expenseCategory, user);
        return expenseRepository.save(transaction);
    }

    public List<Transaction> getAllExpenses(Long userId) {
        return expenseRepository.getTransactionByUserUserId(userId).orElseThrow(() -> new ResourceNotFoundException(TRANSACTION, USER_ID, userId));
    }

    public List<Transaction> getTransactionsBetweenDates(LocalDateTime from, LocalDateTime to, Long userId) {
        return expenseRepository.findByUserUserIdAndCreationDateTimeBetween(userId, new Timestamp(Utility.getMilliSeconds(from)), new Timestamp(Utility.getMilliSeconds(to)));
    }

    public Transaction getTransactionById(Long transactionId) {
        return expenseRepository.findById(transactionId).orElseThrow(() -> new ResourceNotFoundException(TRANSACTION, ID, transactionId));
    }
    public void deleteTransactionById(Long transactionId) {
        expenseRepository.deleteById(transactionId);
    }
    private Transaction getTransactionEntity(ExpensePayload expensePayload, BankAccountInfo bankAccountInfo, ExpenseCategory expenseCategory, User user) {
        return Transaction.builder()
                .id(expensePayload.getId())
                .isExpense(expensePayload.isExpense())
                .amount(expensePayload.getAmount())
                .bankAccountInfo(bankAccountInfo)
                .category(expenseCategory)
                .user(user)
                .paidTo(expensePayload.getPaidTo())
                .description(expensePayload.getDescription())
                .isIncome(expensePayload.isIncome())
                .creationDateTime(expensePayload.getCreationTimeStamp())
                .build();
    }

}

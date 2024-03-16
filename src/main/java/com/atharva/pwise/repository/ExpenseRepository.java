package com.atharva.pwise.repository;

import com.atharva.pwise.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> getTransactionByCategoryId(Long categoryId);
    Optional<List<Transaction>> getTransactionByUserUserId(Long userId);

    List<Transaction> findByUserUserIdAndCreationDateTimeBetween(Long userId, Timestamp from, Timestamp to);

}


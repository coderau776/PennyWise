package com.atharva.pwise.repository;

import com.atharva.pwise.entity.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<ExpenseCategory, Long> {
    void deleteByCategoryNameAndUserUserId(String categoryName, Long userId);
    Optional<ExpenseCategory> findByCategoryNameAndUserUserId(String categoryName, Long userId);

}

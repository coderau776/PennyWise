package com.atharva.pwise.controller;

import com.atharva.pwise.entity.ExpenseCategory;
import com.atharva.pwise.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<ExpenseCategory> createCategory(@RequestBody ExpenseCategory expenseCategory, @RequestParam Long userId) {
        LOGGER.info("Creating new category as {}", expenseCategory.getCategoryName());
        return new ResponseEntity<>(categoryService.addCategory(expenseCategory, userId), HttpStatus.CREATED);
    }

    @DeleteMapping("/category")
    public void deleteCategory(@RequestParam String categoryName, @RequestParam Long userId) {
        categoryService.deleteCategory(categoryName, userId);
    }

}

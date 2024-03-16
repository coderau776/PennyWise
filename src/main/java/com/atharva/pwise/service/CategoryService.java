package com.atharva.pwise.service;

import com.atharva.pwise.entity.ExpenseCategory;
import com.atharva.pwise.exception.ResourceNotFoundException;
import com.atharva.pwise.repository.CategoryRepository;
import com.atharva.pwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.atharva.pwise.utility.Constants.USER;
import static com.atharva.pwise.utility.Constants.USER_ID;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;

    public ExpenseCategory addCategory(ExpenseCategory category, long userId) {
        category.setUser(userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(USER, USER_ID, userId)));
        return categoryRepository.save(category);
    }

    public void deleteCategory(String categoryName, Long userId) {
        categoryRepository.deleteByCategoryNameAndUserUserId(categoryName, userId);
    }

}

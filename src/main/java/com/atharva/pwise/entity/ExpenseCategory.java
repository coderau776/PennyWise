package com.atharva.pwise.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "EXPENSE_CATEGORY",
    indexes = {
        @Index(name = "EXPENSE_CATEGORY_UK", columnList = "category_name, user_id",unique = true)
    }
)
public class ExpenseCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "isCustomCategory")
    private boolean isCustomCategory;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}

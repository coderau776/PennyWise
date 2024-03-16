package com.atharva.pwise.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@Table(name = "USER", uniqueConstraints = {
        @UniqueConstraint(name = "USER_UK",
                columnNames = {"last_name","phone"}
        )
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "budget")
    private Long budget;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creationDateTime")
    @Schema(hidden = true)
    private Timestamp creationDateTime;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Schema(hidden = true)
    private List<ExpenseCategory> expenseCategories;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Schema(hidden = true)
    private List<BankAccountInfo> bankAccounts;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Schema(hidden = true)
    private List<Transaction> transactions;
}

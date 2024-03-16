package com.atharva.pwise.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@Table(name = "TRANSACTION")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "transaction_amount")
    private Long amount;
    @Column(name = "Description")
    private String description;
//    @Column(name = "image")
//    private byte[] photo;
    @Column(name = "PaidTo")
    private String paidTo;
    @Column(name = "isExpense")
    private boolean isExpense;
    //TODO: create new POJO for income
    @Column(name = "isIncome")
    private boolean isIncome;
    @Column(name = "LastUpdatedTime")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp lastUpdatedTime;
    @Column(name = "CreationDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp creationDateTime;
    @ManyToOne
    @JoinColumn(name = "bank_acc_id", nullable = false)
    private BankAccountInfo bankAccountInfo;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ExpenseCategory category;

}

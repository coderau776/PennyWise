package com.atharva.pwise.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TRANSACTION_AUDIT")
public class TransactionAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Action")
    private String action;
    @Column(name = "InsertionDateTime")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp insertionDateTime;
    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String prevState;
    private String currentState;
}

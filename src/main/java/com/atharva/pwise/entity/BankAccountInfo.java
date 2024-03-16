package com.atharva.pwise.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "BANK_ACCOUNT_INFO", uniqueConstraints = {
        @UniqueConstraint(name = "BANK_ACCOUNT_INFO_UK", columnNames = {"bank_account_number","user_id"})
})
public class BankAccountInfo {
    @Id
    @Column(name = "bank_acc_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankAccId;
    @Column(name = "bank_account_number")
    private Long bankAccNumber;
    @Column(name = "bank_name")
    private String bankName;
    @Schema(hidden = true)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creationDateTime")
    private Timestamp creationDateTime;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

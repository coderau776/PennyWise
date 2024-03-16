package com.atharva.pwise.entity.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class ExpensePayload {
    private Long id;
    private Long amount;
    private String description;
    //    @Column(name = "image")
    //    private byte[] photo;
    private String paidTo;
    private boolean isExpense;
    private boolean isIncome;
    private String bankAccNumber;
    private String category;
    private Timestamp creationTimeStamp;
}

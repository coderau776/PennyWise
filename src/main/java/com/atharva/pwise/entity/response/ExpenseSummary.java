package com.atharva.pwise.entity.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class ExpenseSummary {
    private Long budget;
    private Long safeToSpendPerDay;
    private Long totalExpense;
}

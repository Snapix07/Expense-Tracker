package com.snapix.expensetracker.dto.expense;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@Accessors(chain = true)
public class ExpenseResponseDTO {
    private Long id;
    private String description;
    private String category;
    private BigDecimal amount;
    private LocalDateTime date;
}

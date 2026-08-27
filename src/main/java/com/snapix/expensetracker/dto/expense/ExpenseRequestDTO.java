package com.snapix.expensetracker.dto.expense;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ExpenseRequestDTO {
    @NotBlank
    @Size(max = 128)
    private String description;
    @Size(max = 64) @NotBlank
    private String category;
    @Positive
    private BigDecimal amount;
    @PastOrPresent
    private LocalDateTime date;
}

package com.snapix.expensetracker.mapper;

import com.snapix.expensetracker.dto.expense.ExpenseRequestDTO;
import com.snapix.expensetracker.dto.expense.ExpenseResponseDTO;
import com.snapix.expensetracker.entity.Expense;


public class ExpenseMapper {
    public static Expense toEntity(ExpenseRequestDTO dto){
        return new Expense()
                .setAmount(dto.getAmount())
                .setCategory(dto.getCategory())
                .setDate(dto.getDate())
                .setDescription(dto.getDescription());
    }
    public static ExpenseResponseDTO toDto(Expense entity){
        return new ExpenseResponseDTO()
                .setId(entity.getId())
                .setAmount(entity.getAmount())
                .setCategory(entity.getCategory())
                .setDate(entity.getDate())
                .setDescription(entity.getDescription());
    }
}

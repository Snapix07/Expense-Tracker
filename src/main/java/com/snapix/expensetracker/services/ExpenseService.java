package com.snapix.expensetracker.services;

import com.snapix.expensetracker.dto.ExpenseRequestDTO;
import com.snapix.expensetracker.dto.ExpenseResponseDTO;
import com.snapix.expensetracker.entity.Expense;
import com.snapix.expensetracker.exception.ExpenseNotFound;
import com.snapix.expensetracker.mapper.ExpenseMapper;
import com.snapix.expensetracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public ExpenseResponseDTO createExpense(ExpenseRequestDTO expense){
        Expense entity = ExpenseMapper.toEntity(expense);
        Expense saved = expenseRepository.save(entity);
        return ExpenseMapper.toDto(saved);
    }

    public List<ExpenseResponseDTO> readAll(){
        return expenseRepository.findAll().stream().map(expense -> ExpenseMapper.toDto(expense)).toList();
    }

    public ExpenseResponseDTO readById(Long id){
        Expense expense = expenseRepository.findById(id).orElseThrow(()->new ExpenseNotFound(id));
        return ExpenseMapper.toDto(expense);
    }

    public ExpenseResponseDTO updateById(Long id, ExpenseRequestDTO expense){
        Expense updatedExpense = expenseRepository.findById(id).orElseThrow(()-> new ExpenseNotFound(id));

        updatedExpense.setDescription(expense.getDescription());
        updatedExpense.setDate(expense.getDate());
        updatedExpense.setCategory(expense.getCategory());
        updatedExpense.setAmount(expense.getAmount());

        expenseRepository.save(updatedExpense);

        return ExpenseMapper.toDto(updatedExpense);
    }

    public void deleteById(Long id){
        Expense expense = expenseRepository.findById(id).orElseThrow(()-> new ExpenseNotFound(id));
        expenseRepository.deleteById(id);
    }
}

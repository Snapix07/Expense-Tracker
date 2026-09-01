package com.snapix.expensetracker.services;

import com.snapix.expensetracker.dto.expense.ExpenseRequestDTO;
import com.snapix.expensetracker.dto.expense.ExpenseResponseDTO;
import com.snapix.expensetracker.entity.Expense;
import com.snapix.expensetracker.exception.ExpenseAccessDeniedException;
import com.snapix.expensetracker.exception.ExpenseNotFound;
import com.snapix.expensetracker.mapper.ExpenseMapper;
import com.snapix.expensetracker.repository.ExpenseRepository;
import com.snapix.expensetracker.security.CustomUserDetails;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;


    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public ExpenseResponseDTO createExpense(ExpenseRequestDTO expense, CustomUserDetails user){
        Expense entity = ExpenseMapper.toEntity(expense);
        entity.setUser(user.getUser());
        Expense saved = expenseRepository.save(entity);
        return ExpenseMapper.toDto(saved);
    }

    public List<ExpenseResponseDTO> readAll(Long id){
        List<Expense> expense = expenseRepository.findByUser_Id(id);
        return expense.stream().map(exp -> ExpenseMapper.toDto(exp)).toList();
    }

    public ExpenseResponseDTO readById(Long id, Long userId){
        Expense expense = expenseRepository.findById(id).orElseThrow(()->new ExpenseNotFound(id));
        if(expense.getUser().getId().equals(userId)){
            return ExpenseMapper.toDto(expense);
        }
        throw new ExpenseAccessDeniedException("No such expense on user account");
    }

    public ExpenseResponseDTO updateById(Long id, ExpenseRequestDTO expense,Long userId){
        Expense updatedExpense = expenseRepository.findById(id).orElseThrow(()-> new ExpenseNotFound(id));
        if(updatedExpense.getUser().getId().equals(userId)){
            updatedExpense.setDescription(expense.getDescription());
            updatedExpense.setDate(expense.getDate());
            updatedExpense.setCategory(expense.getCategory());
            updatedExpense.setAmount(expense.getAmount());

            expenseRepository.save(updatedExpense);

            return ExpenseMapper.toDto(updatedExpense);
        }
        throw new ExpenseAccessDeniedException("You dont have access to that expense");

    }

    public void deleteById(Long id, Long userId){
        Expense expense = expenseRepository.findById(id).orElseThrow(()-> new ExpenseNotFound(id));
        if(expense.getUser().getId().equals(userId)){
            expenseRepository.deleteById(id);
        } else {
            throw new ExpenseAccessDeniedException("You dont have access to that expense");
        }
    }
}

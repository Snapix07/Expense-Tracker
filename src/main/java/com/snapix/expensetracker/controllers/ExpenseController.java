package com.snapix.expensetracker.controllers;

import com.snapix.expensetracker.dto.expense.ExpenseRequestDTO;
import com.snapix.expensetracker.dto.expense.ExpenseResponseDTO;
import com.snapix.expensetracker.services.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<ExpenseResponseDTO> createExpense(@RequestBody ExpenseRequestDTO expense){
        ExpenseResponseDTO saved = expenseService.createExpense(expense);
        URI location = URI.create("/api/expense/" + saved.getId());
        return ResponseEntity.created(location).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponseDTO>> getAll(){
        return ResponseEntity.ok(expenseService.readAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(expenseService.readById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> update(@PathVariable Long id, @RequestBody ExpenseRequestDTO expense){
        ExpenseResponseDTO saved = expenseService.updateById(id,expense);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        expenseService.deleteById(id);
        return ResponseEntity.status(204).build();
    }
}

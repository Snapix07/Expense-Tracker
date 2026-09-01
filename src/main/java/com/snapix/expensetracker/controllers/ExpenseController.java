package com.snapix.expensetracker.controllers;

import com.snapix.expensetracker.dto.expense.ExpenseRequestDTO;
import com.snapix.expensetracker.dto.expense.ExpenseResponseDTO;
import com.snapix.expensetracker.security.CustomUserDetails;
import com.snapix.expensetracker.services.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<ExpenseResponseDTO> createExpense(@RequestBody ExpenseRequestDTO expense, @AuthenticationPrincipal CustomUserDetails user){
        ExpenseResponseDTO saved = expenseService.createExpense(expense ,user);
        URI location = URI.create("/api/expense/" + saved.getId());
        return ResponseEntity.created(location).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponseDTO>> getAll(@AuthenticationPrincipal CustomUserDetails user){
        Long id = user.getUserId();
        return ResponseEntity.ok(expenseService.readAll(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> getById(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails user){
        return  ResponseEntity.ok(expenseService.readById(id, user.getUserId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponseDTO> update(@PathVariable Long id, @RequestBody ExpenseRequestDTO expense, @AuthenticationPrincipal CustomUserDetails user){
        ExpenseResponseDTO saved = expenseService.updateById(id,expense,user.getUserId());
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,@AuthenticationPrincipal CustomUserDetails user){
        expenseService.deleteById(id, user.getUserId());
        return ResponseEntity.status(204).build();
    }
}

package com.snapix.expensetracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ExpenseNotFound extends RuntimeException {

    public ExpenseNotFound( Long id){
        super("Expense with this id " + id + " not found");

    }
}

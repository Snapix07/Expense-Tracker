package com.snapix.expensetracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ExpenseAccessDeniedException extends RuntimeException {
    public ExpenseAccessDeniedException(String message) {
        super(message);
    }
}

package com.snapix.expensetracker.exception;

public class ExpenseNotFound extends RuntimeException {

    public ExpenseNotFound( Long id){
        super("Expense with this id " + id + " not found");

    }
}

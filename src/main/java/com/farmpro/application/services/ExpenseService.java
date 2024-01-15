package com.farmpro.application.services;

import com.farmpro.application.entities.Expense;
import com.farmpro.application.repositories.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id).get();
    }

    public Expense createExpense(Expense expense){
        return expenseRepository.save(expense);
    }
    public Expense updateExpense (Long id,Expense updatedExpense){

        if(expenseRepository.existsById(id)){
            updatedExpense.setId(id);
            return expenseRepository.save(updatedExpense);
        }
        else{
            throw new NoSuchElementException("Expense object with ID " + id + " not found in the database");
        }
    }
    public void deleteExpense(Long id){
        expenseRepository.deleteById(id);
    }

    public List<Expense> getExpensesByFieldId(Long fieldId) {
        return expenseRepository.findByFieldId(fieldId);

    }
}

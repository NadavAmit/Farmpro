package com.farmpro.application.controllers;

import com.farmpro.application.dto.ExpenseDTO;
import com.farmpro.application.entities.Expense;
import com.farmpro.application.entities.Field;
import com.farmpro.application.services.ExpenseService;
import com.farmpro.application.services.FieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;
    private final FieldService fieldService;


    @GetMapping({"", "/"})
    public ResponseEntity<List<ExpenseDTO>> getAllExpenses(){
        List<Expense> retrievedExpensesList =  expenseService.getAllExpenses();
        List<ExpenseDTO> expensesDTOList = retrievedExpensesList.stream().map(this::mapToDTO).toList();
        return new ResponseEntity<List<ExpenseDTO>>(expensesDTOList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDTO> getExpenseById(@PathVariable("id") Long id){
        Expense retrievedExpense = expenseService.getExpenseById(id);
        ExpenseDTO expenseDTOResponse = mapToDTO(retrievedExpense);
        return new ResponseEntity<ExpenseDTO>(expenseDTOResponse,HttpStatus.OK);
    }

    @GetMapping("/field/{fieldId}")
    public ResponseEntity<List<ExpenseDTO>> getActivitiesByField(@PathVariable Long fieldId) {
        List<Expense> expensesForField = expenseService.getExpensesByFieldId(fieldId);
        List<ExpenseDTO> expenseDTOList = expensesForField.stream()
                .map(this::mapToDTO)
                .toList();
        return new ResponseEntity<List<ExpenseDTO>>(expenseDTOList, HttpStatus.OK);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody ExpenseDTO expenseDTO){
        Field field = fieldService.getFieldById(expenseDTO.getFieldId());
        Expense expense = mapToEntity(expenseDTO,field);
        Expense savedExpense = expenseService.createExpense(expense);
        ExpenseDTO expenseDTOResponse = mapToDTO(savedExpense);
        return new ResponseEntity<ExpenseDTO>(expenseDTOResponse,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDTO> updateExpense(@PathVariable("id") Long id, @RequestBody Expense Expense) {
        Expense savedExpense = expenseService.updateExpense(id,Expense);
        ExpenseDTO expenseDTOResponse = mapToDTO(savedExpense);
        return new ResponseEntity<ExpenseDTO>(expenseDTOResponse,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable("id") Long id) {
        expenseService.deleteExpense(id);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    private ExpenseDTO mapToDTO(Expense expense) {
        ExpenseDTO expenseDTO = new ExpenseDTO();
        expenseDTO.setId(expense.getId());
        expenseDTO.setDate(expense.getDate());
        expenseDTO.setExpenseType(expense.getExpenseType());
        expenseDTO.setAmount(expense.getAmount());
        expenseDTO.setFieldId(expense.getField().getId());
        return expenseDTO;
    }

    private Expense mapToEntity(ExpenseDTO expenseDTO,Field field) {
        return Expense.builder()
                .date(expenseDTO.getDate())
                .expenseType(expenseDTO.getExpenseType())
                .amount(expenseDTO.getAmount())
                .field(field)
                .build();
//        Expense expense = new Expense();
//        expense.setDate(expenseDTO.getDate());
//        expense.setExpenseType(expenseDTO.getExpenseType());
//        expense.setAmount(expenseDTO.getAmount());
//        expenseDTO.setFieldId();
//        return  expenseDTO;
    }
}

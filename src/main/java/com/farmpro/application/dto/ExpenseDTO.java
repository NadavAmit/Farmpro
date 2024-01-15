package com.farmpro.application.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExpenseDTO {

    private long id;
    private long fieldId;
    private String expenseType;
    private long amount;
    private Date date;
}

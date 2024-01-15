package com.farmpro.application.repositories;

import com.farmpro.application.entities.Activity;
import com.farmpro.application.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    List<Expense> findByFieldId(Long fieldId);
}

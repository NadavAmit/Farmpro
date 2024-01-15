package com.farmpro.application.repositories;

import com.farmpro.application.entities.Earning;
import com.farmpro.application.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EarningRepository extends JpaRepository<Earning,Long> {
    List<Earning> findByFieldId(Long fieldId);
}

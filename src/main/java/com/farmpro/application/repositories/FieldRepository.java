package com.farmpro.application.repositories;

import com.farmpro.application.entities.Field;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepository extends JpaRepository<Field,Long> {
}

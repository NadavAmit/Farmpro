package com.farmpro.application.repositories;

import com.farmpro.application.entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity,Long> {
    List<Activity> findByFieldId(Long fieldId);
}

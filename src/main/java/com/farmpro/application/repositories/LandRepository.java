package com.farmpro.application.repositories;

import com.farmpro.application.entities.Land;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LandRepository extends JpaRepository<Land,Long> {
}

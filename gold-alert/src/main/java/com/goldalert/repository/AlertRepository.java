package com.goldalert.repository;

import com.goldalert.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> findByTriggeredFalseAndTargetPriceGreaterThanEqual(double price);

    List<Alert> findByUserEmailOrderByIdDesc(String email);
}

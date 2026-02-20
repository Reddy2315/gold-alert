package com.goldalert.repository;

import com.goldalert.model.GoldPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoldPriceRepository extends JpaRepository<GoldPrice, Long> {
}

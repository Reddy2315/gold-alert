package com.goldalert.repository;

import com.goldalert.model.GoldPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GoldPriceRepository extends JpaRepository<GoldPrice, Long> {
    Optional<GoldPrice> findTopByOrderByFetchedAtDesc();

    List<GoldPrice> findTop20ByOrderByFetchedAtDesc();
}

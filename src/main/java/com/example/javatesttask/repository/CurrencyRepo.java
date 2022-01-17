package com.example.javatesttask.repository;

import com.example.javatesttask.domain.Currency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepo extends JpaRepository<Currency, Long> {
    List<Currency> findByCurrName(String currName);
    Page<Currency> findByCurrName(String currName, Pageable pageable);
}

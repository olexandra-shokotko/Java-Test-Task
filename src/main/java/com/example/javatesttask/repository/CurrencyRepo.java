package com.example.javatesttask.repository;

import com.example.javatesttask.domain.Currency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepo extends JpaRepository<Currency, Long> {
    List<Currency> findAll();
    List<Currency> findByCurrName(String currName);
    Page<Currency> findByCurrName(String currName, Pageable pageable);

    @Query(value = "select * from currency c where curr_name = ?1 order by last_price asc limit 1", nativeQuery = true)
    Currency findMinByCurrName(String currName);

    @Query(value = "select * from currency c where curr_name = ?1 order by last_price desc limit 1", nativeQuery = true)
    Currency findMaxByCurrName(String currName);

    @Query(value = "select curr_name, min(last_price), max(last_price) from currency c group by curr_name", nativeQuery = true)
    List<String []> findMinMax();
}

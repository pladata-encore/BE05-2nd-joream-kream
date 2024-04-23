package com.example.springbootproject.sell.repository;

import com.example.springbootproject.sell.domain.Sell;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellRepository extends JpaRepository<Sell,Long> {
}

package com.example.springbootproject.buy.repository;

import com.example.springbootproject.buy.domain.Buy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyRepository extends JpaRepository<Buy,Long> {
}

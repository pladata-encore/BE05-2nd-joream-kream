package com.example.springbootproject.sell.repository;

import com.example.springbootproject.sell.domain.Sell;
import com.example.springbootproject.size.domain.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellRepository extends JpaRepository<Sell,Long> {
    List<Sell> findAllBySize_IdOrderByPrice(Long id);
}

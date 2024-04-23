package com.example.springbootproject.buy.repository;

import com.example.springbootproject.buy.domain.Buy;
import com.example.springbootproject.size.domain.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BuyRepository extends JpaRepository<Buy,Long> {




}

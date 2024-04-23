package com.example.springbootproject.orderHistory.repository;

import com.example.springbootproject.orderHistory.domain.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory,Long> {
}

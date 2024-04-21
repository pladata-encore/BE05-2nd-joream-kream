package com.example.springbootproject.order.repository;

import com.example.springbootproject.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}

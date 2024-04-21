package com.example.springbootproject.pointHistory.repository;


import com.example.springbootproject.pointHistory.domain.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<PointHistory,Long> {
}

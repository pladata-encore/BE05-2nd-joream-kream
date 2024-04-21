package com.example.springbootproject.point.repository;

import com.example.springbootproject.point.domain.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point,Long> {
}

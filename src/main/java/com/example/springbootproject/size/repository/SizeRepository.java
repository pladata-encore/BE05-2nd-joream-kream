package com.example.springbootproject.size.repository;

import com.example.springbootproject.size.domain.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepository extends JpaRepository<Size,Long> {
}

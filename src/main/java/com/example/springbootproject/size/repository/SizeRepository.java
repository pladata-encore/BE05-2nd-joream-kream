package com.example.springbootproject.size.repository;

import com.example.springbootproject.size.domain.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SizeRepository extends JpaRepository<Size,Long> {
    List<Size> findSizeByProductId(Long productId);
    List<Size> findAllByProductId(Long productId);
}

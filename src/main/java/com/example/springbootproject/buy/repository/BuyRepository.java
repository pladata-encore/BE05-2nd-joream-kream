package com.example.springbootproject.buy.repository;

import com.example.springbootproject.buy.domain.Buy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuyRepository extends JpaRepository<Buy,Long> {
    List<Buy> findByMatchYn(Boolean matchYn);
    List<Buy> findAllBySize_IdAndMatchYnOrderByPriceDesc(Long id, Boolean matchYn);
    Buy findByUserIdAndSizeIdAndPriceAndMatchYnOrderByCreatedAt(Long userId, Long id, Long maxPrice, Boolean matchYn);

}

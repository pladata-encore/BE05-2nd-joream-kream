package com.example.springbootproject.buy.repository;

import com.example.springbootproject.buy.domain.Buy;
import com.example.springbootproject.sell.domain.Sell;
import com.example.springbootproject.size.domain.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BuyRepository extends JpaRepository<Buy,Long> {
    List<Buy> findByMatchYn(Boolean matchYn);
    List<Buy> findAllBySize_IdAndMatchYnOrderByPriceDesc(Long id, Boolean matchYn);
    Buy findByUserIdAndSizeIdAndPriceAndMatchYnOrderByCreatedAt(Long userId, Long id, Long maxPrice, Boolean matchYn);

}

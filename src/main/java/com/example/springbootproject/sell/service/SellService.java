package com.example.springbootproject.sell.service;


import com.example.springbootproject.sell.dto.request.SellRequest;
import com.example.springbootproject.sell.dto.response.MaxPricePerSize;

import java.util.List;

public interface SellService {
    // product Id를 받아서 size별 최소 가격 찾기
    List<MaxPricePerSize> findMaxPricePerSize(Long productId);

    // 입찰 가격을 받아서 구매 요청서 저장
    void savePurchase(Long productId, String sizeValue, Long minPrice, SellRequest sellRequest);

    // 구매 체결은 판매 매물의 가격을 주기적으로 확인해서 진행
    void sellNow(Long productId, String sizeValue, Long minPrice, Long userId);
}

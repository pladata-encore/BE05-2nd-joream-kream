package com.example.springbootproject.buy.service;

import com.example.springbootproject.buy.dto.response.BuyResponse;
import com.example.springbootproject.size.domain.Size;

import java.util.List;

public interface BuyService {

    // size 안에 sizeId와 product Id가 존재한다.
    // userId는 token에서 가져온다.

    // product Id를 이용해서 Size가져옴
    List<Size> findSizeByProductId(Long productId);

    void savePurchase(Long sizeId, Long productId, Long price, Long userId);


    void buyNow(Long sizeId, Long productId, Long price, TokenInfo tokenInfo);
}
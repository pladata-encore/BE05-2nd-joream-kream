package com.example.springbootproject.buy.service;

import com.example.springbootproject.buy.dto.response.MinPricePerSize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BuyService {

    // 해당 product_id를 가진 sell entity를 가져와야 한다.
    // product_id를 가지고 size list를 가져온다.
    // SELECT size_id FROM size WHERE product_id = ? (path variable)

    // 받아온 sizeId를 기준으로 sell entity를 뒤지는데 size와 price만 출력한다.
    // SELECT size.size_value, price FROM sell
    // INNER JOIN size ON size.size_id = ? (받아온 값, loop돌려서 하나씩 빼야함)

    // product Id를 받아서 size별 최소 가격 찾기
    List<MinPricePerSize> findMinPricePerSize(Long productId);

    // 입찰 가격을 받아서 구매 요청서 저장
    void savePurchase(Long productId, String sizeValue, Long price, Integer duration, Long userId);

    // 구매 체결은 판매 매물의 가격을 주기적으로 확인해서 진행
    void buyNow(Long productId, String sizeValue, Long price, Long  userId);

}
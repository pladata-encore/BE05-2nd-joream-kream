package com.example.springbootproject.sell.controller;

import com.example.springbootproject.buy.dto.request.BuyRequest;
import com.example.springbootproject.buy.dto.response.MinPricePerSize;
import com.example.springbootproject.sell.dto.request.SellRequest;
import com.example.springbootproject.sell.dto.response.MaxPricePerSize;
import com.example.springbootproject.sell.service.SellService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
@AllArgsConstructor
public class SellController {

    private final SellService sellService;


    @GetMapping("/product/productdetail/{productId}")
    public List<MaxPricePerSize> getMaxPricePerSize(@PathVariable("productId") Long productId
    ) {
        // token 인증 필요
//        if(bearerToken.isEmpty()) throw new AuthException(AuthErrorCode.PERMISSION_DENIED);
        // 최소 가격 가져오기
        return sellService.findMaxPricePerSize(productId);
    }

    // 구매 버튼에서 상품 선택하고 희망 구매 가격 입력할 때 사용
    // 일반 구매 : 입찰 가격 및 경매 마감 기한을 작성해서 구매 요청서 저장하고 point 차감
//    @RequestHeader TokenInfo tokenInfo
    @PostMapping("sell/{productId}")
    public void askForPurchase(@PathVariable("productId") Long productId,
                               @RequestParam("size") String sizeValue,
                               @RequestParam("maxprice") Long maxPrice,
                               @RequestBody SellRequest sellRequest
    ) {
        // minPrice : front 에서 form data로 쏴줌, URL에 안 들어감
        // userId 는 token 사용
        sellService.savePurchase(productId, sizeValue, maxPrice, sellRequest);

    }

    // 즉시 구매

    @PostMapping("sellnow/{productId}")
    public void sellNow(@PathVariable("productId") Long productId,
                       @RequestParam("size") String sizeValue,
                       @RequestParam("maxprice") Long maxPrice,
                       @RequestBody Long userId) {
        sellService.sellNow(productId, sizeValue, maxPrice, userId);
    }

}

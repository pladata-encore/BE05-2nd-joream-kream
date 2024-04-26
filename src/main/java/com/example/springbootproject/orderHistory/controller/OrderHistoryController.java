package com.example.springbootproject.orderHistory.controller;


import com.example.springbootproject.auth.excrption.AuthErrorCode;
import com.example.springbootproject.auth.excrption.AuthException;
import com.example.springbootproject.config.JwtTokenUtils;
import com.example.springbootproject.config.TokenInfo;
import com.example.springbootproject.orderHistory.dto.response.OrderHistoryResponse;
import com.example.springbootproject.orderHistory.service.OrderHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/auths")
@RequiredArgsConstructor
public class OrderHistoryController {

    private final JwtTokenUtils jwtTokenUtils;
    private final OrderHistoryService orderHistoryService;

@GetMapping("/orderHistory")
    public List<OrderHistoryResponse> getOrderHistory(@RequestHeader("Authorization") String bearerToken){
    TokenInfo tokenInfo = getTokenInfo(bearerToken);

    return orderHistoryService.getOrderHistory(tokenInfo);
}
        private TokenInfo getTokenInfo(String bearerToken) {
        if(bearerToken.isEmpty()) throw new AuthException(AuthErrorCode.PERMISSION_DENIED);
        String token = bearerToken.substring(7);
        TokenInfo tokenInfo = jwtTokenUtils.parseToken(token);
        return tokenInfo;
    }
}



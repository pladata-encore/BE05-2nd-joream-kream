package com.example.springbootproject.orderHistory.service;


import com.example.springbootproject.config.TokenInfo;
import com.example.springbootproject.orderHistory.dto.response.OrderHistoryResponse;

import java.util.List;

public interface OrderHistoryService {
    List<OrderHistoryResponse> getOrderHistory(TokenInfo tokenInfo);
}

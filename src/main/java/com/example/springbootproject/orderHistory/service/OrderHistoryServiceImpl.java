package com.example.springbootproject.orderHistory.service;

import com.example.springbootproject.auth.config.TokenInfo;
import com.example.springbootproject.orderHistory.domain.OrderHistory;
import com.example.springbootproject.orderHistory.dto.response.OrderHistoryResponse;
import com.example.springbootproject.orderHistory.repository.OrderHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderHistoryServiceImpl implements OrderHistoryService {
    private final OrderHistoryRepository orderHistoryRepository;

    @Override
    public List<OrderHistoryResponse> getOrderHistory(TokenInfo tokenInfo) {
       List<OrderHistory>myOrders = orderHistoryRepository.findAllByBuyerIdOrSellerId(tokenInfo.id());

        return myOrders.stream().map((OrderHistory orderHistory) -> OrderHistoryResponse.from(orderHistory, tokenInfo.id())).toList();
    }
}

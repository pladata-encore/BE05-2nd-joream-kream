package com.example.springbootproject.orderHistory.repository;

import com.example.springbootproject.orderHistory.domain.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory,Long> {


 //orderhistory에서 buyerid와 sellerid가 내 id와 맞는 경우
 @Query("select o from OrderHistory o where o.buyerId = :id or o.sellerId =:id")
 List<OrderHistory> findAllByBuyerIdOrSellerId (Long id);
}

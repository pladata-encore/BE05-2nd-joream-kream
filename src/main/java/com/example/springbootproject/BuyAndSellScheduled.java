package com.example.springbootproject;

import com.example.springbootproject.buy.domain.Buy;
import com.example.springbootproject.buy.repository.BuyRepository;
import com.example.springbootproject.sell.repository.SellRepository;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

public class BuyAndSellScheduled {
    private BuyRepository buyRepository;
    private SellRepository sellRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void deleteAfterDuration(){
        // buy 테이블에 등록된 상품을 매일 00시 00분 00초마다
        // buy.getEndAt 과 현재 시간을 비교해서 삭제
        List<Buy> buy = buyRepository.findAll();
        for (Buy buy1 : buy) {
            if(LocalDateTime.now().isAfter(buy1.getEndAt()))
                buyRepository.delete(buy1);
        }

        // sell
    }

}

package com.example.springbootproject;

import com.example.springbootproject.buy.domain.Buy;
import com.example.springbootproject.buy.exception.BuyErrorCode;
import com.example.springbootproject.buy.exception.BuyException;
import com.example.springbootproject.buy.repository.BuyRepository;
import com.example.springbootproject.pointHistory.domain.PointHistory;
import com.example.springbootproject.pointHistory.repository.PointRepository;
import com.example.springbootproject.sell.repository.SellRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class BuyAndSellScheduled {
    private final BuyRepository buyRepository;
    private final SellRepository sellRepository;
    private final PointRepository pointRepository;

    // 하루에 한 번씩 거래 성사 안 된 구매 매물 확인, 거래 마감 시간 지나면 환불하고 지우기
    @Scheduled(cron = "0 0 0 * * *")
    public void deleteAfterDuration(){
        // buy 테이블에 등록된 상품을 매일 00시 00분 00초마다
        // buy.getEndAt 과 현재 시간을 비교해서 삭제
        List<Buy> byMatchYn = buyRepository.findByMatchYn(false);
        for (Buy buy : byMatchYn) {
            if(LocalDateTime.now().isAfter(buy.getEndAt())) {
                // Point history에서 환불 한 줄 추가
                // 1. 필요한 것 : userId, balance, transactionType, createdAt, productName, size, transactionVolume
                // 2. 현재 넣어줄 수 있는 것 : userId, transactionType, createdAt
                // 3. 필요한 것 : balance, productName, size, transactionVolume

                // balance
                List<PointHistory> balanceByUserId = pointRepository.findAllByUserIdOrderByIdDesc(buy.getUser().getId());
                if (balanceByUserId.isEmpty()) throw new BuyException(BuyErrorCode.NO_POINT);
                Long balance = balanceByUserId.get(0).getBalance();

                // productName
                String productName = buy.getSize().getProduct().getName();

                // size
                String size = buy.getSize().getSizeValue();

                // transactionVolume (price)
                Long refundAmount = buy.getPrice();

                // 새로운 잔액
                Long newBalance = balance + refundAmount;

                PointHistory pointHistory = PointHistory.builder()
                        .id(null)
                        .balance(newBalance)
                        .transactionType(true)
                        .createdAt(LocalDateTime.now())
                        .productName(productName)
                        .transactionVolume(refundAmount)
                        .size(size)
                        .user(buy.getUser())
                        .build();

                // point history에 환불 내역 저장
                pointRepository.save(pointHistory);
            }
            buyRepository.delete(buy);
        }

        // sell
    }

}

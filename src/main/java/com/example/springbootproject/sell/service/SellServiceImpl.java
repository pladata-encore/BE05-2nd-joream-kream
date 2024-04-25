package com.example.springbootproject.sell.service;

import com.example.springbootproject.auth.domain.User;
import com.example.springbootproject.auth.repository.AuthRepository;
import com.example.springbootproject.buy.domain.Buy;
import com.example.springbootproject.buy.dto.response.MinPricePerSize;
import com.example.springbootproject.buy.exception.BuyErrorCode;
import com.example.springbootproject.buy.exception.BuyException;
import com.example.springbootproject.buy.repository.BuyRepository;
import com.example.springbootproject.orderHistory.domain.OrderHistory;
import com.example.springbootproject.orderHistory.repository.OrderHistoryRepository;
import com.example.springbootproject.pointHistory.domain.PointHistory;
import com.example.springbootproject.pointHistory.repository.PointRepository;
import com.example.springbootproject.product.domain.Product;
import com.example.springbootproject.product.repository.ProductRepository;
import com.example.springbootproject.sell.domain.Sell;
import com.example.springbootproject.sell.dto.request.SellRequest;
import com.example.springbootproject.sell.dto.response.MaxPricePerSize;
import com.example.springbootproject.sell.exception.SellErrorCode;
import com.example.springbootproject.sell.exception.SellException;
import com.example.springbootproject.sell.repository.SellRepository;
import com.example.springbootproject.size.domain.Size;
import com.example.springbootproject.size.repository.SizeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SellServiceImpl implements SellService {
    private final SizeRepository sizeRepository;
    private final BuyRepository buyRepository;
    private final SellRepository sellRepository;
    private final AuthRepository authRepository;
    private final ProductRepository productRepository;
    private final PointRepository pointRepository;
    private final OrderHistoryRepository orderHistoryRepository;

    @Override
    public List<MaxPricePerSize> findMaxPricePerSize(Long productId) {
        List<MaxPricePerSize> sizeList = new ArrayList<>();
        List<Size> byProductId = sizeRepository.findAllByProduct_Id(productId);
        for (int i = 0; i < byProductId.size(); i++ ) {
            Size sizeObject = byProductId.get(i);
            // size entity 에서 size_value를 받는다.
            String size = sizeObject.getSizeValue();
            // size_id를 가지고 최소 price를 가져온다. 대신 matching 유무가 false인 것만
            List<Buy> bySizeId = buyRepository.findAllBySize_IdAndMatchYnOrderByPriceDesc(sizeObject.getId(), false);
            if (bySizeId.isEmpty()) continue; // 해당 sizeId가 없으면 건너뛴다.

            Long price = bySizeId.get(0).getPrice();
            // 이들을 하나의 record로 묶는다.
            MaxPricePerSize maxPricePerSize = new MaxPricePerSize(size, price);
            // List에 add한다.
            sizeList.add(maxPricePerSize);
        }
        return sizeList;
    }

    @Override
    public void savePurchase(Long productId, String sizeValue, Long maxPrice, SellRequest sellRequest) {
// 구매 요청서 저장
        Long userId = sellRequest.userId();
        Optional<User> userById = authRepository.findById(userId);
        User user = userById.orElseThrow(() -> new SellException(SellErrorCode.USER_NOT_FOUND));
        Optional<Product> productById = productRepository.findById(productId);
        Product product = productById.orElseThrow(() -> new  SellException(SellErrorCode.PRODUCT_NOT_FOUND));
        Size getSize = sizeRepository.findBySizeValueAndProductId(sizeValue,productId);
//        sizeRepository.save(size); // DB에 저장을 해놔야 뒤에 buyRepository에서 문제가 없다.
        Long price = sellRequest.price();
        // 즉시 구매가 보다 작으면 즉시 구매가로 보여줌 @프론트
        Integer duration = sellRequest.duration();
        Sell sell = new Sell(null, user, getSize, price, LocalDateTime.now(), true,LocalDateTime.now().plusDays(duration));
        sellRepository.save(sell);
//        point 차감
//         1. 내 포인트 잔액을 가져온다.
//        List<PointHistory> balanceByUserId = pointRepository.findAllByUserIdOrderByIdDesc(userId);
//        if (balanceByUserId.isEmpty()) throw new SellException(SellErrorCode.NO_POINT);
//        Long balance = balanceByUserId.get(0).getBalance();
        Long balance = 500000L;
        if (balance < price) throw new SellException(SellErrorCode.NO_POINT);
        // 2. 새로운 포인트 내역을 만든다.
        Long newBalance = balance + price; // 포인트 차감
        PointHistory pointHistory = new PointHistory(null
                , newBalance
                , false
                , LocalDateTime.now()
                , product.getName()
                , price
                , sizeValue
                , user);
        pointRepository.save(pointHistory);
        // "order history 등록"
        OrderHistory orderHistory = new OrderHistory(null
                , product.getName()
                , price
                , sizeValue
                , null
                , user.getId() // sellId는 판매자 쪽에서 넣어준다.
                , LocalDateTime.now());
        orderHistoryRepository.save(orderHistory);

        // cron으로 하루에 한 번씩 endAt 확인하고 지나면 매물 삭제 후 돈 환불 @Main

    }

    @Override
    @Transactional
    public void sellNow(Long productId, String sizeValue, Long maxPrice, Long userId) {
        // user 및 product 정보 가져오기
        Optional<User> userById = authRepository.findById(userId);
        User user = userById.orElseThrow(() -> new  SellException(SellErrorCode.USER_NOT_FOUND));
        Optional<Product> productById = productRepository.findById(productId);
        Product product = productById.orElseThrow(() -> new  SellException(SellErrorCode.PRODUCT_NOT_FOUND));



        // point 차감
//        List<PointHistory> balanceByUserId = pointRepository.findAllByUserIdOrderByIdDesc(userId);
//        if (balanceByUserId.isEmpty()) throw new AuthException(AuthErrorCode.NO_POINT);
//        Long balance = balanceByUserId.get(0).getBalance();
        Long balance = 500000L;
//        if (balance < 10000L) throw new  SellException(SellErrorCode.NO_POINT);
        // 2. 새로운 포인트 내역을 만든다.
        Long newBalance = balance +maxPrice; // 포인트 차감
        PointHistory pointHistory = new PointHistory(null
                , newBalance
                , false
                , LocalDateTime.now()
                , product.getName()
                , maxPrice
                , sizeValue
                , user);
        pointRepository.save(pointHistory);

        // order history 업데이트
        OrderHistory orderHistory = new OrderHistory(null
                , product.getName()
                , maxPrice
                , sizeValue
                , null
                , user.getId() // sellId는 판매자 쪽에서 넣어준다.
                , LocalDateTime.now());
        orderHistoryRepository.save(orderHistory);

        // 판매 테이블에서 matchYn = true로 바꿔줌
        // 이걸 위해 sellId가 필요 (판매 쪽에서는 판매가 되면 true)
        Size size = sizeRepository.findByProductIdAndSizeValue(productId, sizeValue);
        // buy 가져오기
        Buy buy = buyRepository.findByUserIdAndSizeIdAndPriceAndMatchYnOrderByCreatedAt(userId, size.getId(), maxPrice, false);
        // sell table의 matchYn 값을 true로 바꿔줌
        buy.setMatchYn(true);

    }
}

package com.example.springbootproject.buy.service;

import com.example.springbootproject.auth.domain.User;
import com.example.springbootproject.auth.excrption.AuthErrorCode;
import com.example.springbootproject.auth.excrption.AuthException;
import com.example.springbootproject.auth.repository.AuthRepository;
import com.example.springbootproject.buy.domain.Buy;
import com.example.springbootproject.buy.dto.request.BuyRequest;
import com.example.springbootproject.buy.dto.response.MinPricePerSize;
import com.example.springbootproject.buy.exception.BuyErrorCode;
import com.example.springbootproject.buy.exception.BuyException;
import com.example.springbootproject.buy.repository.BuyRepository;
import com.example.springbootproject.orderHistory.domain.OrderHistory;
import com.example.springbootproject.orderHistory.repository.OrderHistoryRepository;
import com.example.springbootproject.pointHistory.domain.PointHistory;
import com.example.springbootproject.pointHistory.repository.PointRepository;
import com.example.springbootproject.product.domain.Product;
import com.example.springbootproject.product.exception.ProductErrorCode;
import com.example.springbootproject.product.exception.ProductException;
import com.example.springbootproject.product.repository.ProductRepository;
import com.example.springbootproject.sell.domain.Sell;
import com.example.springbootproject.sell.repository.SellRepository;
import com.example.springbootproject.size.domain.Size;
import com.example.springbootproject.size.exception.SizeErrorCode;
import com.example.springbootproject.size.exception.SizeException;
import com.example.springbootproject.size.repository.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuyServiceImpl implements BuyService {

    private final SizeRepository sizeRepository;
    private final BuyRepository buyRepository;
    private final SellRepository sellRepository;
    private final AuthRepository authRepository;
    private final ProductRepository productRepository;
    private final PointRepository pointRepository;
    private final OrderHistoryRepository orderHistoryRepository;

    // 해당 product_id를 가진 sell entity를 가져와야 한다.
    // product_id를 가지고 size list를 가져온다.
    // SELECT size_id FROM size WHERE product_id = ? (path variable)

    // 받아온 sizeId를 기준으로 sell entity를 뒤지는데 size와 price만 출력한다.
    // SELECT size.size_value, price FROM sell
    // INNER JOIN size ON size.size_id = ? (받아온 값, loop돌려서 하나씩 빼야함)


    @Override
    public List<MinPricePerSize> findMinPricePerSize(Long productId) {
        List<MinPricePerSize> sizeList = new ArrayList<>();
        List<Size> byProductId = sizeRepository.findAllByProduct_Id(productId);
        for (int i = 0; i < byProductId.size(); i++ ) {
            Size sizeObject = byProductId.get(i);
            // size entity 에서 size_value를 받는다.
            String size = sizeObject.getSizeValue();
            // size_id를 가지고 최소 price를 가져온다. 대신 matching 유무가 false인 것만
            List<Sell> bySizeId = sellRepository.findAllBySize_IdAndMatchYnOrderByPrice(sizeObject.getId(), false);
            if (bySizeId.isEmpty()) continue; // 해당 sizeId가 없으면 건너뛴다.

            Long price = bySizeId.get(0).getPrice();
            // 이들을 하나의 record로 묶는다.
            MinPricePerSize minPricePerSize = new MinPricePerSize(size, price);
            // List에 add한다.
            sizeList.add(minPricePerSize);
        }
        return sizeList;
    }

    @Override
    public void savePurchase(Long productId, String sizeValue, Long minPrice, BuyRequest buyRequest) {
        // 구매 요청서 저장
        Long userId = buyRequest.userId();
        Optional<User> userById = authRepository.findById(userId);
        User user = userById.orElseThrow(() -> new BuyException(BuyErrorCode.USER_NOT_FOUND));
        Optional<Product> productById = productRepository.findById(productId);
        Product product = productById.orElseThrow(() -> new  BuyException(BuyErrorCode.PRODUCT_NOT_FOUND));
        Size getSize = sizeRepository.findBySizeValueAndProductId(sizeValue,productId);
//        sizeRepository.save(size); // DB에 저장을 해놔야 뒤에 buyRepository에서 문제가 없다.
        Long price = buyRequest.price();
        // 즉시 구매가 보다 작으면 즉시 구매가로 보여줌 @프론트
        Integer duration = buyRequest.duration();
        Buy buy = new Buy(null, user, getSize, price, LocalDateTime.now(), true, LocalDateTime.now().plusDays(duration));
        buyRepository.save(buy);
//        point 차감
//         1. 내 포인트 잔액을 가져온다.
        List<PointHistory> balanceByUserId = pointRepository.findAllByUserIdOrderByIdDesc(userId);
        if (balanceByUserId.isEmpty()) throw new  BuyException(BuyErrorCode.NO_POINT);
        Long balance = balanceByUserId.get(0).getBalance();
//        Long balance = 500000L;
        if (balance < price) throw new BuyException(BuyErrorCode.NO_POINT);
        // 2. 새로운 포인트 내역을 만든다.
        Long newBalance = balance - price; // 포인트 차감
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
                , user.getId()
                , null // sellId는 판매자 쪽에서 넣어준다.
                , LocalDateTime.now());
        orderHistoryRepository.save(orderHistory);

        // cron으로 하루에 한 번씩 endAt 확인하고 지나면 매물 삭제 후 돈 환불 @Main

    }

    @Override
    public void buyNow(Long productId, String sizeValue, Long minPrice, Long userId) {
        // user 및 product 정보 가져오기
        Optional<User> userById = authRepository.findById(userId);
        User user = userById.orElseThrow(() -> new  BuyException(BuyErrorCode.USER_NOT_FOUND));
        Optional<Product> productById = productRepository.findById(productId);
        Product product = productById.orElseThrow(() -> new  BuyException(BuyErrorCode.PRODUCT_NOT_FOUND));



        // point 차감
//        List<PointHistory> balanceByUserId = pointRepository.findAllByUserIdOrderByIdDesc(userId);
//        if (balanceByUserId.isEmpty()) throw new AuthException(AuthErrorCode.NO_POINT);
//        Long balance = balanceByUserId.get(0).getBalance();
        Long balance = 500000L;
        if (balance < minPrice) throw new  BuyException(BuyErrorCode.NO_POINT);
        // 2. 새로운 포인트 내역을 만든다.
        Long newBalance = balance - minPrice; // 포인트 차감
        PointHistory pointHistory = new PointHistory(null
                , newBalance
                , false
                , LocalDateTime.now()
                , product.getName()
                , minPrice
                , sizeValue
                , user);
        pointRepository.save(pointHistory);

        // order history 업데이트
        OrderHistory orderHistory = new OrderHistory(null
                , product.getName()
                , minPrice
                , sizeValue
                , user.getId()
                , null // sellId는 판매자 쪽에서 넣어준다.
                , LocalDateTime.now());
        orderHistoryRepository.save(orderHistory);

        // 판매 테이블에서 matchYn = true로 바꿔줌
        // 이걸 위해 sellId가 필요 (판매 쪽에서는 판매가 되면 true)


    }



}

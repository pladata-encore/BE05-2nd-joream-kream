package com.example.springbootproject.buy.service;

import com.example.springbootproject.auth.domain.User;
import com.example.springbootproject.auth.repository.AuthRepository;
import com.example.springbootproject.buy.domain.Buy;
import com.example.springbootproject.buy.dto.response.MinPricePerSize;
import com.example.springbootproject.buy.repository.BuyRepository;
import com.example.springbootproject.orderHistory.domain.OrderHistory;
import com.example.springbootproject.orderHistory.repository.OrderHistoryRepository;
import com.example.springbootproject.pointHistory.domain.PointHistory;
import com.example.springbootproject.pointHistory.repository.PointRepository;
import com.example.springbootproject.product.domain.Product;
import com.example.springbootproject.product.repository.ProductRepository;
import com.example.springbootproject.sell.domain.Sell;
import com.example.springbootproject.sell.repository.SellRepository;
import com.example.springbootproject.size.domain.Size;
import com.example.springbootproject.size.repository.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
        int n = sellRepository.findAllBySize_IdOrderByPrice(sizeObject.getId()).size();
        for (int i = 0; i < byProductId.size(); i++ ) {
            Size sizeObject = byProductId.get(i);
            // size에서 size value를 받는다.
            String size = sizeObject.getSizeValue();
//            System.out.println(size);
            // size_id를 가지고 최소 price를 가져온다.
            List<Sell> bySizeId = sellRepository.findAllBySize_IdOrderByPrice(sizeObject.getId());
            if (bySizeId.isEmpty()) throw new IllegalArgumentException("here3");
            Long price = bySizeId.get(0).getPrice();
            // 이들을 하나의 record로 묶는다.
            MinPricePerSize minPricePerSize = new MinPricePerSize(size, price);
            // List에 add한다.
            sizeList.add(minPricePerSize);
            System.out.println(minPricePerSize.size()+"==================================================");
        }
        return sizeList;
    }

    @Override
    public void savePurchase(Long productId, String sizeValue, Long price, Integer duration, Long userId) {
        // 구매 요청서 저장
        Optional<User> userById = authRepository.findById(userId);
        User user = userById.orElseThrow(() -> new IllegalArgumentException("없는 유저입니다."));
        Optional<Product> productById = productRepository.findById(productId);
        Product product = productById.orElseThrow(() -> new IllegalArgumentException("찾는 상품이 존재하지 않습니다."));
        Size size = new Size(null, sizeValue, product);
        Buy buy = new Buy(null, user, size, price, LocalDateTime.now(), false, LocalDateTime.now().plusDays(duration));
        buyRepository.save(buy);
        //point 차감
        // 1. 내 포인트 잔액을 가져온다.
        List<Integer> balanceByUserId = pointRepository.findBalanceByUserIdOrderByIdDesc(userId);
        if (balanceByUserId.isEmpty()) throw new IllegalArgumentException("찾는 상품이 존재하지 않습니다.");
        Integer balance = balanceByUserId.get(0);
        // 2. 새로운 포인트 내역을 만든다.
        Long newBalance = balance - price; // 포인트 차감
        PointHistory pointHistory = new PointHistory(null
                , newBalance
                , false
                , LocalDateTime.now()
                , price
                , product.getName()
                , price
                , size.getSizeValue()
                , user);
        pointRepository.save(pointHistory);
        // "order history 등록"
        OrderHistory orderHistory = new OrderHistory(null
                , product.getName()
                , price
                , size.getSizeValue()
                , user.getId()
                , null
                , LocalDateTime.now());
        orderHistoryRepository.save(orderHistory);
        // cron으로 하루에 한 번씩 endAt 확인하고 지나면 매물 삭제
    }

    @Override
    public void buyNow(Long productId, String sizeValue, Long price, Long userId) {
        // point 차감

        // order history 업데이트

        // 판매 테이블에서 매물 삭제
    }
}

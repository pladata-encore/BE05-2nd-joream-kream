package com.example.springbootproject.buy.service;

import com.example.springbootproject.auth.domain.User;
import com.example.springbootproject.auth.repository.AuthRepository;
import com.example.springbootproject.buy.domain.Buy;
import com.example.springbootproject.buy.dto.response.MinPricePerSize;
import com.example.springbootproject.buy.repository.BuyRepository;
import com.example.springbootproject.product.domain.Product;
import com.example.springbootproject.product.repository.ProductRepository;
import com.example.springbootproject.sell.repository.SellRepository;
import com.example.springbootproject.size.domain.Size;
import com.example.springbootproject.size.repository.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    // 해당 product_id를 가진 sell entity를 가져와야 한다.
    // product_id를 가지고 size list를 가져온다.
    // SELECT size_id FROM size WHERE product_id = ? (path variable)

    // 받아온 sizeId를 기준으로 sell entity를 뒤지는데 size와 price만 출력한다.
    // SELECT size.size_value, price FROM sell
    // INNER JOIN size ON size.size_id = ? (받아온 값, loop돌려서 하나씩 빼야함)

    @Override
    public List<MinPricePerSize> findMinPricePerSize(Long productId) {
        List<MinPricePerSize> sizeList = new ArrayList<>();
        List<Size> byProductId = sizeRepository.findAllByProductId(productId);
        for (int i = 0; i < byProductId.size(); i++ ) {
            Size sizeObject = byProductId.get(i);
            // size에서 size value를 받는다.
            String size = sizeObject.getSizeValue();
            // size_id를 가지고 최소 price를 가져온다.
            Long price = sellRepository.findBySizeIdOrderByPrice(sizeObject.getId()).get(0).getPrice();
            // 이들을 하나의 record로 묶는다.
            MinPricePerSize minPricePerSize = new MinPricePerSize(size, price);
            // List에 add한다.
            sizeList.add(minPricePerSize);
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
        
        // cron으로 duration 동안 판매 매물 확인해서 거래 진행

        // "order history 등록"
    }

    @Override
    public void buyNow(Long productId, String sizeValue, Long price, Long userId) {
        // point 차감

        // order history 업데이트

        // 판매 테이블에서 매물 삭제
    }
}

package com.example.springbootproject.buy.service;

import com.example.springbootproject.auth.domain.User;
import com.example.springbootproject.auth.repository.AuthRepository;
import com.example.springbootproject.buy.domain.Buy;
import com.example.springbootproject.buy.dto.response.BuyResponse;
import com.example.springbootproject.buy.repository.BuyRepository;
import com.example.springbootproject.sell.repository.SellRepository;
import com.example.springbootproject.size.domain.Size;
import com.example.springbootproject.size.repository.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuyServiceImpl implements BuyService {

    private final SizeRepository sizeRepository;
    private final BuyRepository buyRepository;
    private final AuthRepository authRepository;
    private final SellRepository sellRepository;
    private final SizeRepository sizeRepository;


    @Override
    public List<Size> findSizeByProductId(Long productId) {
        return sizeRepository.findSizeByProductId(productId);
    }


    @Override
    public void savePurchase(Long sizeId, Long productId, Long price, TokenInfo tokenInfo) {
        // token으로 유저 인증

        // 구매 요청서 저장
        List<User> usersById = authRepository.findById(userId);
        User user = usersById.get(0);
        Optional<Size> sizeById = sizeRepository.findById(sizeId);
        buyRepository.save(new Buy(null, user, sizeById, price, LocalDateTime.now(),false), null);
    }

    @Override
    public BuyResponse buyNow(Long sizeId, Long productId, Long price, TokenInfo tokenInfo) {
        // token으로 유저 인증

        // 거래 진행
        List<User> usersById = authRepository.findById(userId);
        User user = usersById.get(0);
        Optional<Size> sizeById = sizeRepository.findById(sizeId); // sizeById를


        // order history에 저장


        return null;
    }
}

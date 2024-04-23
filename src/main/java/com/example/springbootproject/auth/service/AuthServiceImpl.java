package com.example.springbootproject.auth.service;

import com.example.springbootproject.auth.domain.User;
import com.example.springbootproject.auth.dto.request.RechargePointsRequest;
import com.example.springbootproject.auth.excrption.AuthErrorCode;
import com.example.springbootproject.auth.excrption.AuthException;
import com.example.springbootproject.auth.repository.AuthRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;
    @Transactional
    @Override
    public void rechargePoints(Long id, RechargePointsRequest req) {
        //10000원부터 충전 가능함
        if(req.chargePoint() < 10000) throw new AuthException(AuthErrorCode.RECHARGE_IS_AVAILABLE_FROM_10000_KRW);
        //회원을 찾아와서 포인트를 충전함

        User user = authRepository.findById(id).orElseThrow(()-> new AuthException(AuthErrorCode.USER_NOT_FOUND));

        user.setPoint(user.getPoint() + req.chargePoint());
    }
}

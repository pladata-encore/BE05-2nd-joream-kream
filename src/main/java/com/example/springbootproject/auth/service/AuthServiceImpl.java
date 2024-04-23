package com.example.springbootproject.auth.service;

import com.example.springbootproject.auth.config.JwtTokenUtils;
import com.example.springbootproject.auth.domain.User;
import com.example.springbootproject.auth.dto.request.LoginRequest;
import com.example.springbootproject.auth.dto.request.SignupRequest;
import com.example.springbootproject.auth.excrption.AuthErrorCode;
import com.example.springbootproject.auth.excrption.AuthException;
import com.example.springbootproject.auth.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.springbootproject.auth.dto.request.RechargePointsRequest;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final AuthRepository authRepository;
    private final JwtTokenUtils jwtTokenUtils;
    // 회원가입
    @Override
    public void signup(SignupRequest signupRequest) {
        String encoded = passwordEncoder.encode(signupRequest.password());
        if(!authRepository.findByEmail(signupRequest.email()).isEmpty())
            throw new AuthException(AuthErrorCode.EMAIL_DUPLICATED);
        User entity = signupRequest.toEntity(encoded);
        authRepository.save(entity);
    }
    // 로그인
    @Override
    public String login(@RequestBody LoginRequest request) {
        //         디비에 있는 것을 찾는다 username 가지고 찾아서
        List<User> byEmail = authRepository.findByEmail(request.email());
        if(byEmail.isEmpty()){
            throw new AuthException(AuthErrorCode.LOGIN_FAIL);
        }
        //        패스워드 비교를 할 것이다
        User user = byEmail.get(0);
        if(!passwordEncoder.matches(request.password(),user.getPassword())){
            throw new AuthException(AuthErrorCode.LOGIN_FAIL);
        }
        //        맞으면 토큰을 리턴 할것이다.
        return jwtTokenUtils.createToken(user);
    }

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

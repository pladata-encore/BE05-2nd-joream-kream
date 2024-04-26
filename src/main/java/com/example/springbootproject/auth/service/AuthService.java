package com.example.springbootproject.auth.service;


import com.example.springbootproject.auth.dto.request.LoginRequest;
import com.example.springbootproject.auth.dto.request.RechargePointsRequest;
import com.example.springbootproject.auth.dto.request.SignupRequest;
import com.example.springbootproject.auth.dto.request.UpdateUserRequest;
import com.example.springbootproject.auth.dto.response.UserInfoResponse;
import com.example.springbootproject.config.TokenInfo;

public interface AuthService {
    void signup(SignupRequest signupRequest);

    String login(LoginRequest request);

    void rechargePoints(TokenInfo tokenInfo, RechargePointsRequest req);

    UserInfoResponse getUserById(TokenInfo tokenInfo);

    void updateUserById(UpdateUserRequest req, TokenInfo tokenInfo);

    String getRefreshToken(TokenInfo tokenInfo);
}

package com.example.springbootproject.auth.service;

import com.example.springbootproject.auth.dto.request.LoginRequest;
import com.example.springbootproject.auth.dto.request.SignupRequest;

public interface AuthService {
    void signup(SignupRequest signupRequest);

    String login(LoginRequest request);
}

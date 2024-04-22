package com.example.springbootproject.auth.service;

import com.example.springbootproject.auth.dto.request.SignupRequest;

public interface AuthService {
    void signup(SignupRequest signupRequest);
}

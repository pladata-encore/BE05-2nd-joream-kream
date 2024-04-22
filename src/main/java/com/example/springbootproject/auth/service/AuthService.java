package com.example.springbootproject.auth.service;

import com.example.springbootproject.auth.dto.request.RechargePointsRequest;

public interface AuthService {
    void rechargePoints(Long id, RechargePointsRequest req);
}

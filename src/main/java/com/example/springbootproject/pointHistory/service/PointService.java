package com.example.springbootproject.pointHistory.service;


import com.example.springbootproject.config.TokenInfo;
import com.example.springbootproject.pointHistory.dto.response.GetPointResponse;

import java.util.List;

public interface PointService {
    List<GetPointResponse> getAllPointHistory(TokenInfo tokenInfo);
}

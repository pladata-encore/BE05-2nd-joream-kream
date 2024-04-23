package com.example.springbootproject.pointHistory.service;

import com.example.springbootproject.pointHistory.domain.PointHistory;
import com.example.springbootproject.pointHistory.dto.request.GetPointRequest;
import com.example.springbootproject.pointHistory.dto.response.GetPointResponse;

import java.util.List;

public interface PointService {
    List<GetPointResponse> getAllPointHistory(Long id);
}

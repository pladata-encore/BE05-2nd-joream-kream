package com.example.springbootproject.pointHistory.service;

import com.example.springbootproject.auth.config.TokenInfo;
import com.example.springbootproject.auth.domain.User;
import com.example.springbootproject.auth.repository.AuthRepository;
import com.example.springbootproject.pointHistory.domain.PointHistory;
import com.example.springbootproject.pointHistory.dto.response.GetPointResponse;
import com.example.springbootproject.pointHistory.excrption.PointErrorCode;
import com.example.springbootproject.pointHistory.excrption.PointException;
import com.example.springbootproject.pointHistory.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {
    private final PointRepository pointRepository;
    private final AuthRepository authRepository;

  public List<GetPointResponse> getAllPointHistory(TokenInfo tokenInfo){
      User user = authRepository.findById(tokenInfo.id()).orElseThrow(()-> new PointException(PointErrorCode.USER_NOT_FOUND));

      List<PointHistory> pointHistory = pointRepository.findAllByUser(user);

      List<GetPointResponse> list = pointHistory.stream().map(GetPointResponse::from).toList();

      return list;
  }
}

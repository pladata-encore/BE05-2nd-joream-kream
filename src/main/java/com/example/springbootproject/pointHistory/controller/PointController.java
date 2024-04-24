package com.example.springbootproject.pointHistory.controller;


import com.example.springbootproject.auth.config.JwtTokenUtils;
import com.example.springbootproject.auth.config.TokenInfo;
import com.example.springbootproject.auth.excrption.AuthErrorCode;
import com.example.springbootproject.auth.excrption.AuthException;
import com.example.springbootproject.pointHistory.domain.PointHistory;
import com.example.springbootproject.pointHistory.dto.request.GetPointRequest;
import com.example.springbootproject.pointHistory.dto.response.GetPointResponse;
import com.example.springbootproject.pointHistory.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/auths")
@RequiredArgsConstructor
public class PointController {
    private final PointService pointService;
    private final JwtTokenUtils jwtTokenUtils;

    @GetMapping("/point")
    public List<GetPointResponse> getAllPointHistory(@RequestHeader("Authorization") String bearerToken){
        TokenInfo tokenInfo = getTokenInfo(bearerToken);


        return pointService.getAllPointHistory(tokenInfo);
    }

    private TokenInfo getTokenInfo(String bearerToken) {
        if(bearerToken.isEmpty()) throw new AuthException(AuthErrorCode.PERMISSION_DENIED);
        String token = bearerToken.substring(7);
        TokenInfo tokenInfo = jwtTokenUtils.parseToken(token);
        return tokenInfo;
    }

}

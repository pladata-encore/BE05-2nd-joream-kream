package com.example.springbootproject.pointHistory.controller;



import com.example.springbootproject.auth.excrption.AuthErrorCode;
import com.example.springbootproject.auth.excrption.AuthException;
import com.example.springbootproject.config.JwtTokenUtils;
import com.example.springbootproject.config.TokenInfo;
import com.example.springbootproject.pointHistory.dto.response.GetPointResponse;
import com.example.springbootproject.pointHistory.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

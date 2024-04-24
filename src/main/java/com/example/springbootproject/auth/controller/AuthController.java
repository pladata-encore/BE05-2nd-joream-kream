package com.example.springbootproject.auth.controller;


import com.example.springbootproject.auth.config.JwtTokenUtils;
import com.example.springbootproject.auth.config.TokenInfo;
import com.example.springbootproject.auth.dto.request.LoginRequest;
import com.example.springbootproject.auth.dto.request.SignupRequest;
import com.example.springbootproject.auth.dto.request.RechargePointsRequest;
import com.example.springbootproject.auth.dto.request.UpdateUserRequest;
import com.example.springbootproject.auth.dto.response.UserInfoResponse;
import com.example.springbootproject.auth.excrption.AuthErrorCode;
import com.example.springbootproject.auth.excrption.AuthException;
import com.example.springbootproject.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auths")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtTokenUtils jwtTokenUtils;
    @GetMapping("/info")
    public UserInfoResponse getUser(@RequestHeader("Authorization") String bearerToken){
        TokenInfo tokenInfo = getTokenInfo(bearerToken);
        return authService.getUserById(tokenInfo);
    }

    @PutMapping("/info")
    public void updateUser(@RequestBody UpdateUserRequest req,@RequestHeader("Authorization") String bearerToken){
        TokenInfo tokenInfo = getTokenInfo(bearerToken);
        authService.updateUserById(req,tokenInfo);
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody SignupRequest request){
        authService.signup(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){
        return authService.login(request);
    }

    @PutMapping("/{id}/point")
    public void rechargePoints(@PathVariable Long id,
                               @RequestBody RechargePointsRequest req){
        authService.rechargePoints(id,req);
    }

    private TokenInfo getTokenInfo(String bearerToken) {
        if(bearerToken.isEmpty()) throw new AuthException(AuthErrorCode.PERMISSION_DENIED);
        return  jwtTokenUtils.parseToken(bearerToken.substring(7));
    }

}

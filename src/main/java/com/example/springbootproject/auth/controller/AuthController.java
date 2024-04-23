package com.example.springbootproject.auth.controller;


import com.example.springbootproject.auth.dto.request.LoginRequest;
import com.example.springbootproject.auth.dto.request.SignupRequest;
import com.example.springbootproject.auth.dto.request.RechargePointsRequest;
import com.example.springbootproject.auth.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auths")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

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
}

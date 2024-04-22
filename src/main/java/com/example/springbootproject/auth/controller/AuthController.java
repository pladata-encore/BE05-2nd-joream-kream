package com.example.springbootproject.auth.controller;

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

    @PostMapping("/create")
    public void create(){

    }

    @PostMapping("/{id}/point")
    public void rechargePoints(@PathVariable Long id,
                               @RequestBody RechargePointsRequest req){
        authService.rechargePoints(id,req);
    }
}

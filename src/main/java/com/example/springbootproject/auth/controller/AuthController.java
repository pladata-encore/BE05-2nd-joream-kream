package com.example.springbootproject.auth.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auths")
@AllArgsConstructor
public class AuthController {
    @PostMapping("/create")
    public void create(){

    }
}

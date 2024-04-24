package com.example.springbootproject.auth.dto.request;

import com.example.springbootproject.auth.domain.User;

public record SignupRequest(String username, String address, String password,String email) {
    public User toEntity(String encodedPassword){
        return new User(
                null,
                username,
                address,
                email,
                encodedPassword,
                0L
        );
    }
}

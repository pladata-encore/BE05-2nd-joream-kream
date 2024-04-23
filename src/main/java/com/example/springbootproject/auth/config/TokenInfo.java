package com.example.springbootproject.auth.config;

import io.jsonwebtoken.Claims;

public record TokenInfo(
        Long id, String nickname
) {
    public static TokenInfo fromClaims(Claims claims){
        Long id = claims.get("id", Long.class);
        String nickname = claims.get("nickname", String.class);
        return new TokenInfo(id, nickname);
    }
}

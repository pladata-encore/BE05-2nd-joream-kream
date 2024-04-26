package com.example.springbootproject.config;

import io.jsonwebtoken.Claims;

public record TokenInfo(
        Long id
) {
    public static TokenInfo fromClaims(Claims claims){
        Long id = claims.get("id", Long.class);
        return new TokenInfo(id);
    }
}

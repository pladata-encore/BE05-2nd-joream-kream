package com.example.springbootproject.auth.config;

import com.example.springbootproject.auth.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenUtils {

//     만료 시간이 존재 한다(ms)
    @Value("${token.expiration}")
     private Long tokenExpiration;
//    secretkey 존재한다
    @Value("${token.secret}")
     private String tokenSecret;


    // 토큰 만들기
     public String createToken(User user) {
         SecretKey secretKey = Keys.hmacShaKeyFor(tokenSecret.getBytes());
         String token = Jwts.builder()
                 .claim("id", user.getId())
                 .signWith(secretKey)
                 .expiration(new Date(new Date().getTime() + tokenExpiration))
                 .compact();
         return token;
     }

    // 토큰 parse
     public TokenInfo parseToken(String token){
         Claims payload = (Claims) Jwts
                 .parser()
                 .verifyWith(Keys.hmacShaKeyFor(tokenSecret.getBytes()))
                 .build()
                 .parse(token)
                 .getPayload();
         System.out.println(payload.getExpiration());
         return TokenInfo.fromClaims(payload);
     }

     //토큰 refresh
    public String refreshToken(TokenInfo tokenInfo) {
        SecretKey secretKey = Keys.hmacShaKeyFor(tokenSecret.getBytes());

        String token = Jwts.builder()
                .claim("id", tokenInfo.id())
                .signWith(secretKey)
                .expiration(new Date(new Date().getTime() + tokenExpiration))
                .compact();
        return token;
    }

}

package com.example.letseat.auth.jwt;


import com.example.letseat.user.User;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration-time}")
    private int expirationTime;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
    public String createToken(User user){
        Date now = new Date();
        String compact = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuedAt(now)//토큰 발급 시간
                .setExpiration(new Date(now.getTime()+ Duration.ofMinutes(expirationTime).toMillis()))
                .claim("id",user.getId())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        return compact;
    }


}

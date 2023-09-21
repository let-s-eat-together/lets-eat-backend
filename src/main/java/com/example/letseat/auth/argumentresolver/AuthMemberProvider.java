package com.example.letseat.auth.argumentresolver;


import com.example.letseat.auth.AuthMember;
import com.example.letseat.auth.jwt.AuthToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;

@RequiredArgsConstructor
@Component
public class AuthMemberProvider {
    @Value("${jwt.secret}")
    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public AuthMember getAuthMember(AuthToken authToken){

        Claims body = Jwts.parser().setSigningKey(secretKey.getBytes())
                .parseClaimsJws(authToken.getToken()).getBody();
        return AuthMember.builder()
                .id(body.get("id",Long.class))
                .build();
    }



}

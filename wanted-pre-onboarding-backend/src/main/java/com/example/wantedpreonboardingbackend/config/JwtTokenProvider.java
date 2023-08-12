package com.example.wantedpreonboardingbackend.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import com.example.wantedpreonboardingbackend.domain.Member;
import com.example.wantedpreonboardingbackend.dto.TokenInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collections;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    private final Key key;

    @Value("${jwt.secret}")
    String salt;

    private final long at_exp = 1000L * 60 * 30;

    public JwtTokenProvider(@Value("${jwt.secret}") String salt) {
        this.key = Keys.hmacShaKeyFor(salt.getBytes(StandardCharsets.UTF_8));
    }

    public TokenInfo generateToken(Member member) {
        // Access Token 생성
        Claims claims = Jwts.claims().setSubject(member.getEmail());
        claims.put("memberId", member.getId());

        long now = (new Date()).getTime();
        Date accessTokenExpiresIn = new Date(now + at_exp);
        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();


        return TokenInfo.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .build();
    }

    public Authentication getAuthentication(String accessToken) {
        System.out.println("accessToken = " + accessToken);
        Claims claims = parseClaims(accessToken);

        UserDetails principal = new User(claims.getSubject(), "", Collections.emptyList());
        return new UsernamePasswordAuthenticationToken(principal, "", Collections.emptyList());
    }

    // 토큰 정보를 검증하는 메서드
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (RuntimeException e) {
            System.out.println("e = " + e);
            throw new RuntimeException(e);
        }
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (RuntimeException e) {
            System.out.println("ParseClaim 오류 = " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public String getMemberId(String token) {
        return Jwts.parserBuilder().setSigningKey(salt.getBytes()).build().parseClaimsJws(token).getBody().getSubject();
    }

    // Bearer 제외부분
    public String getToken(String token) {
        if (token.length() < 7) {
            throw new RuntimeException("토큰에 오류가 있습니다.");
        }
        token = token.substring(7).trim();
        return token;
    }

    public String getEmail(String token) {
        return Jwts.parserBuilder().setSigningKey(salt.getBytes()).build().parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }
}

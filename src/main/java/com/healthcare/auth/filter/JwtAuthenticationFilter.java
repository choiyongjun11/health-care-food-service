package com.healthcare.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthcare.auth.dto.LoginDto;
import com.healthcare.auth.jwt.JwtTokenizer;
import com.healthcare.member.entity.Member;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenizer jwtTokenizer;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenizer jwtTokenizer) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenizer = jwtTokenizer;
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

        ObjectMapper objectMapper = new ObjectMapper();    // (3-1)
        LoginDto loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.class); // (3-2)

        // (3-3)
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        return authenticationManager.authenticate(authenticationToken);  // (3-4)
    }

    // (4)
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws ServletException, IOException {
        Member member = (Member) authResult.getPrincipal();  // (4-1)

        String accessToken = delegateAccessToken(member);   // (4-2)
        String refreshToken = delegateRefreshToken(member); // (4-3)

        response.setHeader("Authorization", "Bearer " + accessToken);  // (4-4)
        response.setHeader("Refresh", refreshToken);

        this.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);

    }

    // (5)
    private String delegateAccessToken(Member member) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", member.getEmail());
        claims.put("roles", member.getRoles());

        String subject = member.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String accessToken = jwtTokenizer.generateAccessToken(claims,subject,expiration,base64EncodedSecretKey);
        return accessToken;
    }

    // (6)
    private String delegateRefreshToken(Member member) {
        String subject = member.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);
        return refreshToken;
    }


}

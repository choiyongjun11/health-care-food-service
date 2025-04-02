package com.healthcare.auth.jwt;

/*
token - secretKey, accessToken - 단시간 유지되는 토큰, refreshToken - 장시간 유지되는 토큰

보통 인증/보안 처리를 할 때, 시간(Time)과 암호화 개념이 중요합니다.
쉽게 말해, A와 B라는 친구가 각각 인증서를 발행했을 때, 두 인증서가 같은지 확인하려면 동일한 시간대에 발행되었는지를 비교해야 합니다.
따라서, 인증 과정에서 시간(Time)은 중요한 요소가 됩니다.

또한, 인증서를 비밀리에 주고받느냐, 아니면 공개적으로 교환하느냐에 따라 방식이 달라집니다.
쉽게 생각하면, 서로만 알고 있는 암호 형태로 글을 작성해 비밀 편지를 주고받는 것과 유사합니다.

  jwt:
    key: ${JWT_SECRET_KEY}
    access-token-expiration-minutes: 30
    refresh-token-expiration-minutes: 420

  application.yml 파일에 아래 value 값을 추가하고 일치한지 확인을 해야한다.  @Gatter,@value - springframework 애너테이션을 사용하자.
 */

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenizer {
    @Getter
    @Value("${jwt.key}")
    private String secretKey;

    @Getter
    @Value("${jwt.access-token-expiration-minutes}")
    private int accessTokenExpirationMinutes; //토큰 만료 시간

    @Getter
    @Value("${jwt.refresh-token-expiration-minutes}")
    private int refreshTokenExpirationMinutes; //토큰 만료 시간

    public String encodeBase64SecretKey(String secretKey) { //secretKey 발행

        //UTF_8 한국어 지원 가능한 인코더 방식으로 BASE64 암호화를 하고자 합니다.
        return Encoders.BASE64.encode(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    //access Token 발행
    public String generateAccessToken(Map<String, Object> clamis,
                                      String subject,
                                      Date expiration,
                                      String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setClaims(clamis)
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    //refresh 토큰
    public String generateRefreshToken(String subject, Date expiration, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    public Jws<Claims> getClaims(String jws, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws);
    }


    public void verifySignature(String jws, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws);
    }

    public Date getTokenExpiration(int expiration) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expiration);

        return calendar.getTime();
    }

    public Key getKeyFromBase64EncodedKey(String base64EncodedSecretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);

        return Keys.hmacShaKeyFor(keyBytes);
    }



}

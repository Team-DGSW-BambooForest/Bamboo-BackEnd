package com.bamboo.userservice.global.jwt;


import com.bamboo.userservice.domain.user.UserEntity;
import com.bamboo.userservice.domain.user.domain.repository.UserRepository;
import com.bamboo.userservice.global.config.AppProperties;
import com.bamboo.userservice.global.config.JwtProperties;
import com.bamboo.userservice.global.enums.JwtType;
import com.bamboo.userservice.global.exception.GlobalException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
@Slf4j
@Service
@RequiredArgsConstructor
public class TokenProvider {

    private final JwtProperties jwtProperties;
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256; //해쉬암호 HS256
    private final AppProperties appProperties;
    private final UserRepository userRepository;

    public String generateToken(Integer userId, JwtType jwtType) {
        Date expiration = new Date();
        expiration = (jwtType == JwtType.ACCESS_TOKEN)
                ? new Date(expiration.getTime() + jwtProperties.getAccessExpire())
                : new Date(expiration.getTime() + jwtProperties.getRefreshExpire());
        String secretKey = (jwtType == JwtType.ACCESS_TOKEN)
                ? appProperties.getSecret()
                : appProperties.getRefreshSecret();

        Claims claims = Jwts.claims();
        claims.put("userId", userId);
        log.info("[{}]", expiration);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(jwtType.toString())
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .signWith(SIGNATURE_ALGORITHM, secretKey)
                .compact();
    }

    private Claims parseToken(String token, JwtType jwtType) {
        try {
            return Jwts.parser()
                    .setSigningKey((jwtType == JwtType.ACCESS_TOKEN)
                            ? appProperties.getSecret()
                            : appProperties.getRefreshSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new GlobalException(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다.");
        } catch (SignatureException | MalformedJwtException e) {
            throw new GlobalException(HttpStatus.UNAUTHORIZED, "위조된 토큰입니다.");
        } catch (IllegalArgumentException e) {
            throw new GlobalException(HttpStatus.BAD_REQUEST, "토큰이 존재하지 않습니다.");
        } catch (Exception e) {
            throw new GlobalException(HttpStatus.INTERNAL_SERVER_ERROR, "토큰 서비스와의 오류가 발생하였습니다.");
        }
    }

    public UserEntity validateToken(String token) {
        return userRepository.findById(
                        Integer.valueOf(parseToken(token, JwtType.ACCESS_TOKEN)
                                .get("userId")
                                .toString())
                )
                .orElseThrow(UserEntity.NotFoundException::new);
    }

    public String refreshToken(String refreshToken) {
        if (refreshToken == null || refreshToken.trim().isEmpty()) {
            throw new GlobalException(HttpStatus.BAD_REQUEST, "토큰이 존재하지 않습니다.");
        }

        Claims claims = parseToken(refreshToken, JwtType.REFRESH_TOKEN);
        UserEntity member = userRepository
                .findById(Integer.parseInt(claims.get("userId").toString()))
                .orElseThrow(UserEntity.NotFoundException::new);
        return generateToken(member.getUserId(), JwtType.ACCESS_TOKEN);
    }
}
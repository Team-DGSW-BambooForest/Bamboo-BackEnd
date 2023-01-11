package com.bamboo.userservice.global.jwt;


import com.bamboo.userservice.domain.user.UserEntity;
import com.bamboo.userservice.domain.user.domain.repository.UserRepository;
import com.bamboo.userservice.global.config.AppProperties;
import com.bamboo.userservice.global.exception.GlobalException;
import com.bamboo.userservice.global.type.JwtAuth;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
@Slf4j
@Service
@RequiredArgsConstructor
public class TokenProvider {
    private static final long JWT_ACCESS_EXPIRE = 1000 * 60 * 60 * 24;  //1일
    private static final long JWT_REFRESH_EXPIRE = 1000 * 60 * 60 * 24 * 7;  //7일
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256; //해쉬암호 HS256
    private final AppProperties appProperties;
    private final UserRepository userRepository;

    //AccessToken, RefreshToken 생성
    public String generateToken(Integer userId, JwtAuth jwtAuth) {
        Date expiration = new Date();
        expiration = (jwtAuth == JwtAuth.ACCESS_TOKEN)
                ? new Date(expiration.getTime() + JWT_ACCESS_EXPIRE)
                : new Date(expiration.getTime() + JWT_REFRESH_EXPIRE);
        String secretKey = (jwtAuth == JwtAuth.ACCESS_TOKEN)
                ? appProperties.getSecret()
                : appProperties.getRefreshSecret();

        Claims claims = Jwts.claims();
        claims.put("userId", userId);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(jwtAuth.toString())
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .signWith(SIGNATURE_ALGORITHM, secretKey)
                .compact();
    }

    private Claims parseToken(String token, JwtAuth jwtAuth) {
        try {
            return Jwts.parser()
                    .setSigningKey((jwtAuth == JwtAuth.ACCESS_TOKEN)
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
                        Integer.valueOf(parseToken(token, JwtAuth.ACCESS_TOKEN)
                                .get("userId")
                                .toString())
                )
                .orElseThrow(UserEntity.NotFoundException::new);
    }

    //refreshToken으로 AccessToken발급
    public String refreshToken(String refreshToken) {
        if (refreshToken == null || refreshToken.trim().isEmpty()) {
            throw new GlobalException(HttpStatus.BAD_REQUEST, "토큰이 존재하지 않습니다.");
        }

        Claims claims = parseToken(refreshToken, JwtAuth.REFRESH_TOKEN);
        UserEntity member = userRepository
                .findById(Integer.parseInt(claims.get("userId").toString()))
                .orElseThrow(UserEntity.NotFoundException::new);
        return generateToken(member.getUserId(), JwtAuth.ACCESS_TOKEN);
    }
}
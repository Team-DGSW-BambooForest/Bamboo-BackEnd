package com.bamboo.userservice.global.jwt;


import com.bamboo.userservice.domain.token.exception.TokenExpirationException;
import com.bamboo.userservice.domain.token.exception.TokenForgeryException;
import com.bamboo.userservice.domain.token.exception.TokenInternalServerErrorException;
import com.bamboo.userservice.domain.token.exception.TokenNotFoundException;
import com.bamboo.userservice.domain.user.domain.UserEntity;
import com.bamboo.userservice.domain.user.domain.exception.UserNotFoundException;
import com.bamboo.userservice.domain.user.domain.repository.UserRepository;
import com.bamboo.userservice.domain.user.domain.type.Role;
import com.bamboo.userservice.global.config.AppProperties;
import com.bamboo.userservice.global.config.JwtProperties;
import com.bamboo.userservice.global.enums.JwtType;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
@RequiredArgsConstructor
public class TokenProvider {

    private final JwtProperties jwtProperties;
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256; //해쉬암호 HS256
    private final AppProperties appProperties;
    private final UserRepository userRepository;

    public String generateToken(String name, String profileImage, Role role, JwtType jwtType) {
        Date expiration = new Date();
        expiration = (jwtType == JwtType.ACCESS_TOKEN)
                ? new Date(expiration.getTime() + jwtProperties.getAccessExpire())
                : new Date(expiration.getTime() + jwtProperties.getRefreshExpire());
        String secretKey = (jwtType == JwtType.ACCESS_TOKEN)
                ? appProperties.getSecret()
                : appProperties.getRefreshSecret();

        return Jwts.builder()
                .claim("name", name)
                .claim("profileImage", profileImage)
                .claim("role", role)
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
            throw TokenExpirationException.EXCEPTION;
        } catch (SignatureException | MalformedJwtException e) {
            throw TokenForgeryException.EXCEPTION;
        } catch (IllegalArgumentException e) {
            throw TokenNotFoundException.EXCEPTION;
        } catch (Exception e) {
            throw TokenInternalServerErrorException.EXCEPTION;
        }
    }

    public UserEntity validateToken(String token) {
        return userRepository.findByName(
                        (parseToken(token, JwtType.ACCESS_TOKEN)
                                .get("name")
                                .toString())
                )
                .orElseThrow(() ->
                        UserNotFoundException.EXCEPTION
                );
    }

    public String refreshToken(String refreshToken) {
        if (refreshToken == null || refreshToken.trim().isEmpty()) {
            throw TokenNotFoundException.EXCEPTION;
        }

        Claims claims = parseToken(refreshToken, JwtType.REFRESH_TOKEN);
        UserEntity member = userRepository
                .findByName(claims.get("name").toString())
                .orElseThrow(() ->
                        UserNotFoundException.EXCEPTION
                );
        return generateToken(member.getName(), member.getProfileImage(), member.getRole(), JwtType.ACCESS_TOKEN);
    }
}
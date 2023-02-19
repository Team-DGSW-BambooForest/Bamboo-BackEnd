package com.bamboo.postservice.global.jwt;

import com.bamboo.postservice.global.client.ConfigProperties;
import com.bamboo.postservice.global.exception.BusinessException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtUtil {

    private final ConfigProperties configProperties;

    public String getSubject(String token) {
        return Jwts.parser().setSigningKey(configProperties.getAccessKey()).parseClaimsJws(token).getBody().getSubject();
    }

    public String extract(HttpServletRequest request, String type) {
        Enumeration<String> headers = request.getHeaders("Authorization");
        while (headers.hasMoreElements()) {
            String value = headers.nextElement();
            if(value.toLowerCase().startsWith(type.toLowerCase())) {
                return value.substring(type.length()).trim();
            }
        }
        return Strings.EMPTY;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(configProperties.getAccessKey())
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (ExpiredJwtException e) {
            throw new BusinessException(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다.");
        } catch (SignatureException | MalformedJwtException e) {
            throw new BusinessException(HttpStatus.UNAUTHORIZED, "위조된 토큰입니다");
        } catch (IllegalArgumentException e) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "토큰이 존재하지 않습니다");
        }
    }

}

package com.bamboo.postservice.global.jwt;

import com.bamboo.postservice.global.client.ConfigProperties;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final ConfigProperties configProperties;

    public String getSubject(String token) {
        return Jwts.parser().setSigningKey(configProperties.getSecretKey()).parseClaimsJws(token).getBody().getSubject();
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
}

package com.bamboo.adminservice.global.util;

import io.jsonwebtoken.Jwts;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Component
public class AuthorizationUtil {

    @Value("${jwt.secret-key}")
    private static String secretKey;

    public static String getSubject(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token).getBody().getSubject();
    }

    public static String extract(HttpServletRequest request, String type) {
        Enumeration<String> headers = request.getHeaders("Authorization");

        while (headers.hasMoreElements()) {
            String value = headers.nextElement();
            if (value.startsWith(type)) {
                return value.substring(type.length()).trim();
            }
        }

        return Strings.EMPTY;
    }
}

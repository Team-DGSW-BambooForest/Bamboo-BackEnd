package com.bamboo.commentsservice.global.interceptor;

import com.bamboo.commentsservice.global.annotation.AuthToken;
import com.bamboo.commentsservice.global.client.ConfigProperties;
import com.bamboo.commentsservice.global.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    private final ConfigProperties configProperties;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if(!(handlerMethod.getMethod().isAnnotationPresent(AuthToken.class))) {
            return true;
        }

        String token = jwtUtil.extract(request, "Bearer");

        if(token.equals("")) {
            request.setAttribute("author", "익명의 대소고인");
            request.setAttribute("profileImage", configProperties.getProfileUrl());
            return true;
        }

        jwtUtil.validateToken(token);

        request.setAttribute("author", jwtUtil.getUsername(token));
        request.setAttribute("profileImage",jwtUtil.getProfileImage(token));
        return true;

    }

}

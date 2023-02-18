package com.bamboo.postservice.global.interceptor;

import com.bamboo.postservice.global.jwt.JwtUtil;
import com.bamboo.postservice.global.annotation.AuthToken;
import lombok.RequiredArgsConstructor;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

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
            request.setAttribute("user", "익명의 대소고인");
            throw new IllegalArgumentException("anonymous");
        }

        String userName = jwtUtil.getSubject(token);
        request.setAttribute("user", userName);

        return true;
    }

}

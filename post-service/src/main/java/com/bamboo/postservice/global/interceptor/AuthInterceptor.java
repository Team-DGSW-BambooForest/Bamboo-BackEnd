package com.bamboo.postservice.global.interceptor;

import com.bamboo.postservice.global.jwt.JwtType;
import com.bamboo.postservice.global.jwt.JwtUtil;
import com.bamboo.postservice.global.annotation.AuthToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
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
            request.setAttribute("author", "익명의 대소고인");
            return true;
        }

        jwtUtil.validateToken(token);

        String userName = jwtUtil.getUsername(token);
        String profileImage = jwtUtil.getProfileImage(token);
        request.setAttribute("author", userName);
        request.setAttribute("profileImage", profileImage);
        return true;
    }

}

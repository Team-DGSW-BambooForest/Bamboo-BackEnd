package com.bamboo.userservice.global.interceptor;

import com.bamboo.userservice.domain.user.UserEntity;
import com.bamboo.userservice.global.annotations.AuthToken;
import com.bamboo.userservice.global.jwt.TokenProvider;
import com.bamboo.userservice.global.util.AuthorizationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
@Component
@RequiredArgsConstructor
public class MyInterceptor implements HandlerInterceptor {

    private final TokenProvider tokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {
        log.info("[{}] request uri: {}", request.getMethod(), request.getRequestURI());

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (!handlerMethod.getMethod().isAnnotationPresent(AuthToken.class)) {
            return true;
        }

        String token = AuthorizationUtil.extract(request, "Bearer");
        if (token.equals("")) {
            throw new UserEntity.UnAuthenticationException();
        }

        UserEntity user = tokenProvider.validateToken(token);
        request.setAttribute("user", user);

        return true;
    }
}
package com.bamboo.adminservice.global.intercetor;

import com.bamboo.adminservice.global.annotation.AuthToken;
import com.bamboo.adminservice.global.exception.GlobalException;
import com.bamboo.adminservice.global.util.AuthorizationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class HttpIntercetor implements HandlerInterceptor {
    private final AuthorizationUtil authorizationUtil;
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {


        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (!handlerMethod.getMethod().isAnnotationPresent(AuthToken.class)) {
            return true;
        }

        String token = authorizationUtil.extract(request, "Bearer");
        if (token.equals("")) {
            throw new GlobalException(HttpStatus.NOT_FOUND ,"토큰이 없어요");
        }

        String role = authorizationUtil.getSubject(token);
        request.setAttribute("role", role);

        return true;
    }
}

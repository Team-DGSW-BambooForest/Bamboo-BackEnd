package com.bamboo.adminservice.global.intercetor;

import com.bamboo.adminservice.global.annotation.AuthToken;
import com.bamboo.adminservice.global.util.AuthorizationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class HttpIntercetor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        log.info("왜안돼");

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        log.info("1");
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (!handlerMethod.getMethod().isAnnotationPresent(AuthToken.class)) {
            return true;
        }
        log.info("2");
        String token = AuthorizationUtil.extract(request, "Bearer");
        if (token.equals("")) {
            throw new RuntimeException("토큰이 없어요");
        }
        log.info("3");
        String user = AuthorizationUtil.getSubject(token);
        request.setAttribute("user", user);
        log.info("{}", user);

        return true;
    }
}

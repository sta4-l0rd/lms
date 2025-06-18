package com.sta4l0rd.lms.interceptor;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoggingInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("[preHandle][" + request + "]" + "[" + request.getMethod()
                + "]" + request.getRequestURI() + "[Parameters Passed :"
                + new ObjectMapper().writeValueAsString(request.getParameterMap()) + "]");
        return true;
    }
}

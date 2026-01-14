package com.perpustakaan.service_buku.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // Mencatat request masuk (Metode apa, ke URL mana, dari IP mana)
        log.info("REQUEST: {} {} from {}", request.getMethod(), request.getRequestURI(), request.getRemoteAddr());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // Mencatat respon keluar (Status sukses 200 atau error 400/500)
        log.info("RESPONSE: {} {} - Status: {}", request.getMethod(), request.getRequestURI(), response.getStatus());
    }
}
package com.robert.exam.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.UUID;

@Component
public class LogginFilter extends OncePerRequestFilter {
    private static Logger log = LogManager.getLogger();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String non = UUID.randomUUID().toString();
        MDC.put("id", non);
        log.info("Inicia peticion a {}", request.getRequestURI());
        long t1 = System.currentTimeMillis();
        filterChain.doFilter(request, response);
        long t2 = System.currentTimeMillis();

        afterRequest(wrapResponse(response), (t2 - t1));
        MDC.remove("id");
    }

    protected void afterRequest(ContentCachingResponseWrapper response, long time){
        try {
            String responseText = new String(response.getContentAsByteArray(), response.getCharacterEncoding());
            MDC.put("TiempoTotal", "TiempoTotal: " + String.valueOf(time));
            log.info("Response {} ", responseText);
            MDC.remove("TiempoTotal");
        } catch (java.io.UnsupportedEncodingException e) {
            log.info("incidencia {}", e.getMessage());
        }
    }

    private static ContentCachingResponseWrapper wrapResponse(HttpServletResponse response) {
        if (response instanceof ContentCachingResponseWrapper) {
            return (ContentCachingResponseWrapper) response;
        } else {
            return new ContentCachingResponseWrapper(response);
        }
    }
}

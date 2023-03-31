package com.robert.exam.filter;

import com.robert.exam.util.Helper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Component
public class LoggerFilter extends OncePerRequestFilter {
    private static Logger log = LogManager.getLogger();

    @Autowired
    Helper helper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        MDC.put("id", helper.getUUID());

        log.info("Inicia peticion a {}", request.getRequestURI());

        long t1 = System.currentTimeMillis();
        filterChain.doFilter(request, response);
        long t2 = System.currentTimeMillis();

        afterRequest(wrapResponse(response), (t2 - t1));
        MDC.remove("id");
    }

    protected void afterRequest(ContentCachingResponseWrapper response, long time){
        try {
            StringBuilder builder = new StringBuilder()
                    .append("Response ## ")
                    .append(response.getStatus())
                    .append(" ")
                    .append(HttpStatus.valueOf(response.getStatus()).getReasonPhrase());
            log.info(builder.toString());

            byte[] responseString = StreamUtils.copyToByteArray(response.getContentInputStream());
            String responseText = new String(responseString, response.getCharacterEncoding());
            response.copyBodyToResponse();
            responseText = responseText.replace("\r\n|\r|\n", " ").replace("\"", "'");

            MDC.put("TiempoTotal", "TiempoTotal: " + String.valueOf(time));
            log.info("Response {} ", responseText);
            MDC.remove("TiempoTotal");
        } catch (Exception e) {
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

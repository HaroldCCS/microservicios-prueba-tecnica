package com.aldemao.lines.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ApiKeyInterceptor implements HandlerInterceptor {

    @Value("${microservice.api.key}")
    private String apiKey;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestApiKey = request.getHeader("x-api-key");

        if (requestApiKey == null || requestApiKey.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            String jsonResponse = "{"
                    + "\"status\": 401,"
                    + "\"message\": \"access key mandatory\","
                    + "\"data\": \"\""
                    + "}";
            response.getWriter().write(jsonResponse);
            return false;
        }

        if (!apiKey.equals(requestApiKey)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            String jsonResponse = "{"
                    + "\"status\": 401,"
                    + "\"message\": \"access key invalid\","
                    + "\"data\": \"\""
                    + "}";
            response.getWriter().write(jsonResponse);
            return false;
        }

        return true;
    }
}

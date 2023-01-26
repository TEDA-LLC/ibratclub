package com.ibratclub.config;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Malikov Azizjon  *  17.01.2023  *  22:23   *  IbratClub
 */

@Component
@RequiredArgsConstructor
public class PreFilter extends OncePerRequestFilter {

    @Value("${telegram.bot.token}")
    String botToken;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "*");
        response.addHeader("Access-Control-Allow-Methods", "*");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        String token = request.getHeader("Authorization");
        if (token == null || token.length() <= 8 || !token.substring(7).equals(botToken)) {
            if (!request.getServletPath().startsWith("/api/site") && !request.getServletPath().startsWith("/api/vacancy") && !request.getMethod().equals("GET")
                    && !request.getMethod().equals("POST") ) { //&& !request.getMethod().equals("POST") && !request.getMethod().equals("GET") && !request.getMethod().equals("PUT")) {
                response.getWriter().print("{\"message\":\"Forbidden!\",\"success\":false,\"status\":403}");
                response.setContentType("application/json");
                if (!request.getMethod().equalsIgnoreCase("OPTIONS")) {
                    response.setStatus(403);
                }
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

}

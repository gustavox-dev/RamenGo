package com.example.RamenGo.filter;

import com.example.RamenGo.exceptions.UnauthorisedException;
import com.example.RamenGo.infra.RestErrorMessage;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {
    private static final String X_API_KEY = "x-api-key";
    public static final String API_KEY = "ZtVdh8XQ2U8pWI2gmZ7f796Vh8GllXoN7mr0djNf";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String apiKey = request.getHeader(X_API_KEY);

        String requestURI = request.getRequestURI();
        if ((API_KEY.equals(apiKey)) ||
                (requestURI != null && (requestURI.contains("swagger-ui") || requestURI.contains("h2-console") || requestURI.contains("v3/api-docs")))) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            RestErrorMessage errorMessage = new RestErrorMessage("x-api-key header missing");
            response.getWriter().write(errorMessage.getError());
        }
    }
}

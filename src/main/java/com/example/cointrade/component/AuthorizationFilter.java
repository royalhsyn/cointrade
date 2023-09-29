package com.example.cointrade.component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.cointrade.entity.User;
import com.example.cointrade.repository.UserRepo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

import static org.springframework.security.authentication.UsernamePasswordAuthenticationToken.authenticated;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    @Value("${app.jwt.secret}")
    private String secret;

    private final UserRepo userRepo;

    public AuthorizationFilter(AuthenticationManager authenticationManager, UserRepo userRepo) {
        super(authenticationManager);
        this.userRepo = userRepo;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        try {
            if (header != null && header.startsWith("Bearer ")) {
                DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(secret))
                        .build()
                        .verify(header.substring(7));
                String username = decodedJWT.getSubject();
                Authentication authentication = userRepo.findByUsername(username)
                        .map(User::getAuthorities)
                        .map(grantedAuthorities -> authenticated(username,
                                header.substring(7),
                                grantedAuthorities))
                        .orElse(null);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            chain.doFilter(request, response);
        } catch (TokenExpiredException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        }
    }
}


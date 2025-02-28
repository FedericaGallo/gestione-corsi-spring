package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.http.HttpHeaders;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtFilter(JwtService jwtService, UserDetailsService userDetailsService){
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }
    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain) throws ServletException, IOException {
if(request.getServletPath().contains("/auth")){
    filterChain.doFilter(request, response);
    return;
}
final String authHeader = request.getHeader("Authorization");
System.out.println("header " + authHeader);
final String jwt;
final String userEmail;
if(authHeader == null || !authHeader.startsWith("Bearer")){
    filterChain.doFilter(request, response);
    return;
}
jwt = authHeader.substring(7);
userEmail = jwtService.extractUsername(jwt);
System.out.println("useramil ");
if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
    UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
    if(jwtService.isTokenValid(jwt, userDetails)){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
        authToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

}
        filterChain.doFilter(request, response);
    }

}

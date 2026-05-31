package com.example.HireDesk.Util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
@Configuration
public class JWTFilter extends OncePerRequestFilter {
    private JwtUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            // Validate the token and set authentication in the security context
            // You can use a JWT library like jjwt to validate the token and extract user details
            if(token!=null && !token.equals("") && jwtUtil.isTokenValid(token)) {
                String userId= jwtUtil.extractId(token);
                String adminEmail= jwtUtil.extractAdminEmail(token);
                String role= jwtUtil.extractRole(token);
                // Set the authentication in the security context (you can create a custom Authentication object)
                    List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));
                // 2. Create Authentication object
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                adminEmail,
                                null,
                                authorities
                        );
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }
}

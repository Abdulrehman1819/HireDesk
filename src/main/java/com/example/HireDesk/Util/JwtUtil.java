package com.example.HireDesk.Util;

import com.example.HireDesk.dto.TenantLoginRequestDTO;
import com.example.HireDesk.model.Tenant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUtil {
    @Value("${jwt.secret}")
    public String secret;
    @Value("${jwt.expiration}")
    public String expiration;
    public String generateToken(Tenant tenant) {
        return Jwts.builder()
                .claim("id", tenant.getId())
                .claim("adminEmail", tenant.getAdminEmail())
                .claim("adminRole",tenant.getRole())
                .setSubject(tenant.getId().toString())
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, secret)
                .compact();
    }

public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()  ;
}
public String extractAdminEmail(String token) {
    return extractAllClaims(token).get("adminEmail", String.class);
}
public String extractRole(String token) {
        return extractAllClaims(token).get("adminRole", String.class);
}
public String extractId(String token) {
        return extractAllClaims(token).get("id", String.class);
}
    public Boolean isTokenExpired(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getExpiration().before(new Date());
    }
public Boolean validateToken(String token,Tenant tenant) {
        String userId=extractId(token);
        return (userId.equals(tenant.getId()) && !isTokenExpired(token));
}
    public boolean isTokenValid(String token) {
        try {
            extractAllClaims(token);
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }
}

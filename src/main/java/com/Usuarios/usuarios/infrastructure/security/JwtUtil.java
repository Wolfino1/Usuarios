package com.Usuarios.usuarios.infrastructure.security;

import com.Usuarios.common.configurations.utils.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private static final String SECRET_KEY       = Constants.SECRET_KEY;
    private static final long   EXPIRATION_TIME  = Constants.EXPIRATION_TIME;

    public String generateToken(Object principal) {
        CustomUserDetails userDetails = (CustomUserDetails) principal;
        Map<String, Object> claims = new HashMap<>();

        // Rol
        String fullRole = userDetails.getAuthorities().stream()
                .findFirst()
                .map(a -> a.getAuthority().startsWith("ROLE_")
                        ? a.getAuthority().substring(5)
                        : a.getAuthority()
                )
                .orElse("UNKNOWN");
        claims.put("role", fullRole);

        Long sellerId = userDetails.getId();
        claims.put("sellerId", sellerId);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(
                        Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)),
                        SignatureAlgorithm.HS256
                )
                .compact();
    }

    /**
     * Expone los Claims para que otros servicios puedan extraer cualquier dato.
     */
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Long extractSellerId(String token) {
        Object claim = extractAllClaims(token).get("sellerId");
        if (claim instanceof Number) {
            return ((Number) claim).longValue();
        } else if (claim instanceof String) {
            return Long.parseLong((String) claim);
        }
        return null;
    }
    
    public boolean isTokenExpired(String token) {
        Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}


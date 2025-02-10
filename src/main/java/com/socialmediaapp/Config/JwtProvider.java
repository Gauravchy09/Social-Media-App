package com.socialmediaapp.Config;

import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.security.core.Authentication;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.JwtParser;

public class JwtProvider {

    private static final SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECERT_KEY.getBytes());
    private static final JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(key).build();

    public static String generateToken(Authentication auth) {
        return Jwts.builder()
            .setIssuer("Gaurav")
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // Token valid for 1 day
            .claim("email", auth.getName())
            .signWith(key)
            .compact();
    }

    public static String getEmailFromJwtToken(String jwt) {
        if (jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);
        }

        Claims claims = jwtParser.parseClaimsJws(jwt).getBody();
        return claims.get("email", String.class);
    }
}

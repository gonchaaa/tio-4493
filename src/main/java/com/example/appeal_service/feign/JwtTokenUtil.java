//package com.example.appeal_service.feign;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//
//@Component
//public class JwtTokenUtil {
//
//    @Value("${securty.jwt.secret}")
//    private String SECRET_KEY;
//
//    private Key getKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    public Long extractUserId(String token) {
//        Claims claims = Jwts.parserBuilder()
//                .setAllowedClockSkewSeconds(60)
//                .setSigningKey(getKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//
//        Object userIdObj = claims.get("userId");
//
//        if (userIdObj == null) {
//            throw new RuntimeException("Token-də userId tapılmadı.");
//        }
//
//        if (userIdObj instanceof Integer) {
//            return ((Integer) userIdObj).longValue();
//        } else if (userIdObj instanceof Long) {
//            return (Long) userIdObj;
//        } else if (userIdObj instanceof String) {
//            return Long.parseLong((String) userIdObj);
//        } else {
//            throw new RuntimeException("Token-də userId tipi tanınmadı.");
//        }
//    }
//
//}

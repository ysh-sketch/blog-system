package com.example.blog.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

public class JwtUtil {

    // 密钥（签名用）
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Token 有效期：7 天
    private static final long EXPIRATION_TIME = 7 * 24 * 60 * 60 * 1000;

    // 生成 Token
    public static String generateToken(String username, Long userId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username) // 用户名
                .claim("userId", userId) // 用户ID
                .setIssuedAt(now) // 签发时间
                .setExpiration(expiryDate) // 过期时间
                .signWith(SECRET_KEY) // 签名
                .compact();
    }

    // 验证 Token 并获取用户名
    public static String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    // 验证 Token 并获取用户ID
    public static Long extractUserId(String token) {
        Claims claims = getClaims(token);
        return claims.get("userId", Long.class);
    }

    // 验证 Token 是否有效
    public static boolean validateToken(String token) {
        try {
            getClaims(token);
            return !isTokenExpired(getClaims(token));
        } catch (Exception e) {
            return false;
        }
    }

    // 解析 Token
    private static Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 检查 Token 是否过期
    private static boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }
}
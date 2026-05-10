package org.example.week09zonghe.util;

import lombok.extern.slf4j.Slf4j;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类：模拟登录认证
 */
@Slf4j
public class JwtUtil {

    // JWT密钥（实际项目应该从配置文件读取）
    private static final String SECRET_KEY = "my-secret-key-for-jwt-token-generation-2024";
    
    // Token过期时间：2小时（毫秒）
    private static final long EXPIRATION_TIME = 2 * 60 * 60 * 1000;

    // 生成密钥
    private static SecretKey getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 生成JWT Token
     * @param username 用户名
     * @param role 角色
     * @return JWT Token字符串
     */
    public static String generateToken(String username, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("role", role);

        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * 解析JWT Token
     * @param token JWT Token字符串
     * @return Claims对象
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * 从Token中获取用户名
     * @param token JWT Token字符串
     * @return 用户名
     */
    public static String getUsername(String token) {
        return parseToken(token).getSubject();
    }

    /**
     * 从Token中获取角色
     * @param token JWT Token字符串
     * @return 角色
     */
    public static String getRole(String token) {
        return parseToken(token).get("role", String.class);
    }

    /**
     * 验证Token是否有效
     * @param token JWT Token字符串
     * @return 是否有效
     */
    public static boolean validateToken(String token) {
        try {
            Claims claims = parseToken(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            log.error("Token验证失败", e);
            return false;
        }
    }
}

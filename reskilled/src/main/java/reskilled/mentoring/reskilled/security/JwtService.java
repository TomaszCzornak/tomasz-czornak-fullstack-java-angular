package reskilled.mentoring.reskilled.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {

    public JwtService(@Value("${jwt.secret}") String secret) {
        SECRET = secret;
    }

    public final String SECRET ;

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String userName, int exp) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName, exp);
    }

    private String createToken(Map<String, Object> claims, String userName, int exp) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + exp))
                .signWith(getSignKey())
                .compact();
    }

}

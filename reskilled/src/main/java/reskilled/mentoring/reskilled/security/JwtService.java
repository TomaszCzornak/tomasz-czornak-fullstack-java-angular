package reskilled.mentoring.reskilled.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {

    private final CookieService cookieService;
    private final int refreshExp;
    private final int exp;

    private final HttpServletResponse httpServletResponse;

    public JwtService(CookieService cookieService, @Value("${jwt.secret}") String secret,
                      @Value("${jwt.refresh.exp}") int refreshExp, @Value("${jwt.exp}") int exp, HttpServletResponse httpServletResponse) {
        this.refreshExp = refreshExp;
        this.exp = exp;
        this.cookieService = cookieService;
        this.httpServletResponse = httpServletResponse;
        SECRET = secret;
    }



    public final String SECRET ;

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String   generateToken(String userName, int exp) {
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

    public String refreshToken(final String token, int exp){
        String username = getSubject(token);
        return generateToken(username,exp);
    }

    public String getSubject(final String token){
        return Jwts
                .parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public void validateToken(HttpServletRequest request) throws ExpiredJwtException, IllegalArgumentException{
        String token = null;
        String refresh = null;
        if (request.getCookies() != null){
            for (Cookie value : Arrays.stream(request.getCookies()).toList()) {
                if (value.getName().equals("Authorization")) {
                    token = value.getValue();
                } else if (value.getName().equals("refresh")) {
                    refresh = value.getValue();
                }
            }
        }else {
            throw new IllegalArgumentException("Token can't be null");
        }
        try {
            validateToken(token);
        }catch (IllegalArgumentException | ExpiredJwtException e){
            validateToken(refresh);
            Cookie refreshCokkie = cookieService.generateCookie("refresh", refreshToken(refresh,refreshExp), refreshExp);
            Cookie cookie = cookieService.generateCookie("Authorization", refreshToken(refresh,exp), exp);
            httpServletResponse.addCookie(cookie);
            httpServletResponse.addCookie(refreshCokkie);
        }

    }

    public void validateToken(final String token) throws ExpiredJwtException, IllegalArgumentException {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }

}

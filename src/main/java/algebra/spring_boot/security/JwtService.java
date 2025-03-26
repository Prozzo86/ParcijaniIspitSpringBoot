package algebra.spring_boot.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtService {

    private final String SECRET_KEY = "YR6NKUdqjvelpRGGQs91jFBEPnhCVo0Irsx1uHCn4eNYbaMPpuMEs4M832bYVKc7"; //64 charactera mora biti key

    public boolean validateToken(String token, UserDetails userDetails){
        String username = extractUsername(token);
        boolean isUsernameValid = username.equals(userDetails.getUsername());

        if (!isUsernameValid){
            return false;
        }

        boolean isTokenExpired = checkIsTokenExpired(token);

        if (isTokenExpired) {
            return false;
        }

        return true;
    }

    private boolean checkIsTokenExpired(String token){
        Date expirationDate = extractExpirationDate(token);
        boolean isExpired = expirationDate.before(new Date());
        return isExpired;
    }

    public String extractUsername (String token){
        Claims allClaims = extractAllClaims(token);
        return allClaims.getSubject();
    }

    public Date extractExpirationDate (String token) {
        Claims allClaims = extractAllClaims(token);
        return allClaims.getExpiration();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateAccessToken(String subject){
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // traje 1h
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String generateRefreshToken(String subject){
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 100)) // traje 100h
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}

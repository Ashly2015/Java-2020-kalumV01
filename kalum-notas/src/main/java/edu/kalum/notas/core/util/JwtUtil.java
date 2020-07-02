package edu.kalum.notas.core.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Date;
import java.util.function.Function;

public class JwtUtil {
    private String SECRET_KEY="8db21ae2d039901d6a2cb92dbaab6286562345e4";
    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).parseClaimsJwt(token).getBody();
    }
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);

    }
    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new  Date());
    }
    public boolean validateToken(String token){
        return (isTokenExpired(token));
    }

    public UserDetails extractUserDetails(String token){
        UserDetails userDetails=null;
        return userDetails;

    }
}

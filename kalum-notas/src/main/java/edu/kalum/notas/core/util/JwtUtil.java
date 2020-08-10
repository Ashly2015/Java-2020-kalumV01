package edu.kalum.notas.core.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
@Service
public class JwtUtil {
    @Value("${edu.kalum.notas.secret}")
    private String SECRET_KEY;

    public UserDetails extractUserDetails(String token){
        UserDetails userDetails=null;
        Claims claims =extractAllClaims(token);
        if (!claims.isEmpty()){
            String username=claims.get("user_name").toString();
            String roles[]=claims.get("authorities").toString().
                    replace("[","").
                    replace("]","").split(",");
            List<GrantedAuthority> authorities= Arrays.stream(roles).map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toList());
            userDetails =new User(username,username,authorities);
        }

        return userDetails;

    }
    public boolean validateToken(String token){
        return (!isTokenExpired(token));
    }
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);

    }
    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).parseClaimsJws(token).getBody();
    }
}

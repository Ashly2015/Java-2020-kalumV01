package edu.kalum.notas.core.filters;

import edu.kalum.notas.core.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        UserDetails userDetails=null;
        String jwt=null;
        final String authorizationHeader= request.getHeader("Authorization");
        if (authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){
            try {
                jwt=authorizationHeader.substring(7);
                if (jwtUtil.validateToken(jwt)){
                    userDetails=jwtUtil.extractUserDetails(jwt);
                }
            }catch (IllegalArgumentException e){
                    System.out.println("Unable to get JWT Token");
            }catch (ExpiredJwtException e){
                System.out.println("JWT Token has expired");
            }
        }
        if(userDetails !=null && SecurityContextHolder.getContext().getAuthentication()==null){
            if (jwtUtil.validateToken(jwt)){
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new
                    UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        }
        filterChain.doFilter(request,response);
    }
}

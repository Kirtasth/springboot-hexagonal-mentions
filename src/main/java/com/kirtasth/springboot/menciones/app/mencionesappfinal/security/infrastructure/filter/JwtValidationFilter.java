package com.kirtasth.springboot.menciones.app.mencionesappfinal.security.infrastructure.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.HttpStatusCodes;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class JwtValidationFilter extends BasicAuthenticationFilter {

    private final SecretKey secret;

    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.secret = Keys.hmacShaKeyFor(Dotenv.load().get("SECRET_KEY").getBytes());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader("Authorization");

        if (header == null) {
            chain.doFilter(request, response);
            return;
        }
        if (!header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace("Bearer ", "");

        try {
            Claims claims = Jwts.parser().verifyWith(this.secret).build().parseSignedClaims(token).getPayload();
            String username = claims.getSubject();
            Object authoritiesClaims = claims.get("authorities");

            Collection<? extends  GrantedAuthority> authorities = List.of();

            if (authoritiesClaims != null){
                @SuppressWarnings(value = "unchecked")
                List<String> authorityList = (List<String>) authoritiesClaims;
                authorities = authorityList.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

            }

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username,null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            chain.doFilter(request,response);
        } catch (JwtException ex){
            Map<String, String> body = new HashMap<>();
            body.put("error", ex.getMessage());
            body.put("message", "Token JWT not valid");

            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            response.setStatus(HttpStatusCodes.STATUS_CODE_UNAUTHORIZED);
            response.setContentType("application/json");
        }
    }
}

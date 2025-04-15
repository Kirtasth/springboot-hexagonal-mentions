package com.kirtasth.springboot.menciones.app.mencionesappfinal.security.infrastructure.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.HttpStatusCodes;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.user.infrastructure.rest.UserDto;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final SecretKey secret;

    private final int expiration;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        String encodedKey = Dotenv.load().get("SECRET_KEY");
        this.secret = Keys.hmacShaKeyFor(encodedKey.getBytes());
        this.expiration = Integer.parseInt(Dotenv.load().get("TOKEN_EXPIRATION"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            UserDto userDto = new ObjectMapper().readValue(request.getInputStream(), UserDto.class);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword());

            return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        List<String> authorities = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        Claims claims = Jwts.claims()
                .add("authorities", authorities)
                .add("username", user.getUsername())
                .build();

        String token = Jwts.builder()
                .subject(user.getUsername())
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + this.expiration))
                .issuedAt(new Date())
                .signWith(this.secret)
                .compact();
        response.addHeader("Authorization", "Bearer " + token);

        Map<String, String> body = new HashMap<>();
        body.put("token", token);
        body.put("username", user.getUsername());
        body.put("message", "OK");

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setContentType("application/json");
        response.setStatus(HttpStatusCodes.STATUS_CODE_OK);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Map<String, String> body = new HashMap<>();
        body.put("message","Authentication error, user or password incorrect!");
        body.put("error", failed.getMessage());

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setContentType("application/json");

        response.setStatus(HttpStatusCodes.STATUS_CODE_UNAUTHORIZED);
    }
}

package com.kirtasth.springboot.menciones.app.mencionesappfinal.security.infrastructure;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.security.infrastructure.filter.JwtAuthenticationFilter;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.security.infrastructure.filter.JwtValidationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SpringSecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.authorizeHttpRequests((authz) -> authz
                        .requestMatchers(HttpMethod.POST,"/users/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/give/").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/list").hasAuthority("ADMIN")
                        .anyRequest().hasAuthority("USER"))
                        .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                        .addFilter(new JwtValidationFilter(authenticationManager()))
                        .csrf(AbstractHttpConfigurer::disable)
                        .sessionManagement(management ->
                                management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .build();
    }
}

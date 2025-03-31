package com.kirtasth.springboot.menciones.app.mencionesappfinal.security.application;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.user.domain.UserRepository;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.user.infrastructure.rest.UserDto;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.user.infrastructure.rest.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    private final UserDtoMapper userDtoMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = userDtoMapper.toUserDto(userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("Usuario " + username + " no encontrado.")));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                user.isAccountNonExpired(),
                user.isCredentialsNonExpired(),
                user.isAccountNonLocked(),
                user.getAuthorities()
        );
    }
}

package com.kirtasth.springboot.menciones.app.mencionesappfinal.user.application;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.role.domain.RoleRepository;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.role.domain.model.Role;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.user.domain.UserRepository;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.user.domain.UserService;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.user.domain.model.User;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.listAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public User save(User user) {
        Optional<Role> optionalRole = roleRepository.findByName("USER");
        List<Role> roles = new ArrayList<>();
        optionalRole.ifPresent(roles::add);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User giveRole(User user, Role role){
        List<Role> roles = user.getRoles();
        roles.add(role);
        user.setRoles(roles);
        return user;
    }

}

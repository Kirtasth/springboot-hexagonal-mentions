package com.kirtasth.springboot.menciones.app.mencionesappfinal.user.domain;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.role.domain.model.Role;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.user.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    User save(User user);

    User giveRole(User user, Role role);
}

package com.kirtasth.springboot.menciones.app.mencionesappfinal.role.domain;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.role.domain.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {

    List<Role> findAll();

    Optional<Role> findById(Long id);

    Optional<Role> findByName(String name);

    Role save(Role role);

    void delete(Role role);
}

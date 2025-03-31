package com.kirtasth.springboot.menciones.app.mencionesappfinal.role.domain;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.role.domain.model.Role;

import java.util.Optional;

public interface RoleService {

    Optional<Role> findByName(String name);

    Role save(Role role);
}

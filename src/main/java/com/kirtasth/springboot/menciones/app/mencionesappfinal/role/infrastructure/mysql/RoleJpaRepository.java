package com.kirtasth.springboot.menciones.app.mencionesappfinal.role.infrastructure.mysql;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.role.infrastructure.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleJpaRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(String name);
}

package com.kirtasth.springboot.menciones.app.mencionesappfinal.user.infrastructure.mysql;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.user.infrastructure.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
}

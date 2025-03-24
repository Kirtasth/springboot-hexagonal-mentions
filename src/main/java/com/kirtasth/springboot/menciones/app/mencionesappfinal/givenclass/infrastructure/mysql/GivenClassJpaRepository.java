package com.kirtasth.springboot.menciones.app.mencionesappfinal.givenclass.infrastructure.mysql;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.givenclass.infrastructure.GivenClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GivenClassJpaRepository extends JpaRepository<GivenClassEntity, UUID> {

    Optional<GivenClassEntity> findById(Long id);

    void deleteById(Long id);
}

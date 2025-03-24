package com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.infrastructure.mysql;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.infrastructure.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProfessorJpaRepository extends JpaRepository<ProfessorEntity, Long> {

}

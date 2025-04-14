package com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.domain;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.domain.model.Professor;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository {

    Professor save(Professor professor);

    Optional<Professor> findById(Long id);

    List<Professor> findAll();

    void deleteById(Long id);

}

package com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.domain;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.domain.model.Professor;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.domain.model.ProfessorRole;

import java.util.List;
import java.util.Optional;

public interface ProfessorService {

    Optional<Professor> findById(Long id);

    Professor save(Professor professor);

    List<Professor> allProfessorByRole(ProfessorRole professorRole);

    List<Professor> findAll();

}

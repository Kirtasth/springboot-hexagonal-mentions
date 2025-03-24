package com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.infrastructure.controller;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.domain.model.ProfessorRole;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.infrastructure.rest.ProfessorDto;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface ProfessorController {

    ResponseEntity<List<ProfessorDto>> listByRole(ProfessorRole role);

    ResponseEntity<List<ProfessorDto>> listAll();

    ResponseEntity<ProfessorDto> save(ProfessorDto professorDto);

}

package com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.infrastructure.rest;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.domain.model.Professor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProfessorDtoMapper {

    Professor toProfessor(ProfessorDto professorDto);

    ProfessorDto toProfessorDto(Professor professor);
}

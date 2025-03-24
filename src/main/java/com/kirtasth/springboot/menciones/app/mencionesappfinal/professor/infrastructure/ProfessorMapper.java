package com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.infrastructure;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.domain.model.Professor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProfessorMapper {

    Professor toProfessor(ProfessorEntity professorEntity);

    ProfessorEntity toProfessorEntity(Professor professor);

}

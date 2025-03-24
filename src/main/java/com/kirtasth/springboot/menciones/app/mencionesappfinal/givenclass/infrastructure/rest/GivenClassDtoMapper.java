package com.kirtasth.springboot.menciones.app.mencionesappfinal.givenclass.infrastructure.rest;


import com.kirtasth.springboot.menciones.app.mencionesappfinal.course.domain.model.Course;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.givenclass.domain.model.GivenClass;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.domain.model.Professor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GivenClassDtoMapper {

    @Mapping(source = "professor", target = "professor")
    @Mapping(source = "course", target = "course")
    @Mapping(source = "givenClassDto.id", target = "id")
    GivenClass toGivenClass(GivenClassDto givenClassDto, Professor professor, Course course);

    @Mapping(target = "professorId", expression = "java(givenClass.getProfessor().getId())")
    @Mapping(target = "courseId", expression = "java(givenClass.getCourse().getId())")
    GivenClassDto toGivenClassDto(GivenClass givenClass);

}

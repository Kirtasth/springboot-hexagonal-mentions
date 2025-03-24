package com.kirtasth.springboot.menciones.app.mencionesappfinal.givenclass.infrastructure;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.givenclass.domain.model.GivenClass;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GivenClassMapper {

    @Mapping(source = "professorEntity", target = "professor")
    @Mapping(source = "courseEntity", target = "course")
    GivenClass toGivenClass(GivenClassEntity givenClassEntity);

    @Mapping(source = "professor", target = "professorEntity")
    @Mapping(source = "course", target = "courseEntity")
    GivenClassEntity toGivenClassEntity(GivenClass givenClass);

}

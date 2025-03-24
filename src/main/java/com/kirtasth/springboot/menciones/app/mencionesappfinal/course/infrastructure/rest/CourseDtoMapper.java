package com.kirtasth.springboot.menciones.app.mencionesappfinal.course.infrastructure.rest;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.course.domain.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CourseDtoMapper {

    Course toCourse(CourseDto courseDto);

    CourseDto toCourseDto(Course course);
}

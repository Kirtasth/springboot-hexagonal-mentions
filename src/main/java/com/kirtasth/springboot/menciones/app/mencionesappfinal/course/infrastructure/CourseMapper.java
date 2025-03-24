package com.kirtasth.springboot.menciones.app.mencionesappfinal.course.infrastructure;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.course.domain.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CourseMapper {

    Course toCourse(CourseEntity courseEntity);

    CourseEntity toCourseEntity(Course course);

}

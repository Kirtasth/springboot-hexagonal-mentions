package com.kirtasth.springboot.menciones.app.mencionesappfinal.course.infrastructure.controller;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.course.infrastructure.rest.CourseDto;
import org.springframework.http.ResponseEntity;

import java.time.DayOfWeek;
import java.util.List;

public interface CourseController {

    ResponseEntity<CourseDto> save(CourseDto courseDto);

    ResponseEntity<CourseDto> findById(Long id);

    ResponseEntity<List<CourseDto>> listAll();

    ResponseEntity<List<CourseDto>> findByDayOfWeek(DayOfWeek dayOfWeek);

    ResponseEntity<List<CourseDto>> findByName(String name);

}

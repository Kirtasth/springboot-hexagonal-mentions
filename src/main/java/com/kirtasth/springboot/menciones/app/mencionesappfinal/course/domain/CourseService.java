package com.kirtasth.springboot.menciones.app.mencionesappfinal.course.domain;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.course.domain.model.Course;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

public interface CourseService {

    Course save(Course course);

    Optional<Course> findById(Long id);

    List<Course> listAll();

    void deleteById(Long id);

    List<Course> findByDayOfWeek(DayOfWeek dayOfWeek);

    List<Course> findByName(String name);
}

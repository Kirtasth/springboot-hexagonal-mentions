package com.kirtasth.springboot.menciones.app.mencionesappfinal.course.infrastructure.mysql;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.course.infrastructure.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CourseJpaRepository extends JpaRepository<CourseEntity, Long> {

}

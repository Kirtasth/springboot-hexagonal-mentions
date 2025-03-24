package com.kirtasth.springboot.menciones.app.mencionesappfinal.course.infrastructure.mysql;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.course.domain.CourseRepository;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.course.domain.model.Course;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.course.infrastructure.CourseEntity;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.course.infrastructure.CourseMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CourseRepositoryAdapter implements CourseRepository {

    private final CourseJpaRepository courseJpaRepository;
    private final CourseMapper courseMapper;

    @Override
    public Course save(Course course) {
        CourseEntity courseEntity = courseJpaRepository.save(courseMapper.toCourseEntity(course));
        return courseMapper.toCourse(courseEntity);
    }

    @Override
    public Optional<Course> findById(Long id) {
        return courseJpaRepository.findById(id).map(courseMapper::toCourse);
    }

    @Override
    public List<Course> listAll() {
        return courseJpaRepository.findAll().stream().map(courseMapper::toCourse).toList();
    }

    @Override
    public void deleteById(Long id) {
        courseJpaRepository.deleteById(id);
    }

    @Override
    public List<Course> findByDayOfWeek(DayOfWeek dayOfWeek) {
        return courseJpaRepository.findAll().stream().filter(c ->
                c.getDayOfWeek().equals(dayOfWeek)).map(courseMapper::toCourse).toList();
    }

    @Override
    public List<Course> findByName(String name) {
        return courseJpaRepository.findAll().stream().filter(c ->
                c.getName().equals(name)).map(courseMapper::toCourse).toList();
    }

}

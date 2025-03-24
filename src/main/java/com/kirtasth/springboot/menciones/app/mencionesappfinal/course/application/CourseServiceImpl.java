package com.kirtasth.springboot.menciones.app.mencionesappfinal.course.application;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.course.domain.CourseRepository;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.course.domain.CourseService;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.course.domain.model.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;


    @Override
    @Transactional
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> listAll() {
        return courseRepository.listAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findByDayOfWeek(DayOfWeek dayOfWeek) {
        return courseRepository.findByDayOfWeek(dayOfWeek);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findByName(String name) {
        return courseRepository.findByName(name);
    }
}

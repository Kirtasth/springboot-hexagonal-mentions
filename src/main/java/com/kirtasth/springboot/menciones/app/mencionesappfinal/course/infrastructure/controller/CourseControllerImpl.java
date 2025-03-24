package com.kirtasth.springboot.menciones.app.mencionesappfinal.course.infrastructure.controller;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.course.domain.CourseService;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.course.infrastructure.rest.CourseDto;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.course.infrastructure.rest.CourseDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/courses")
public class CourseControllerImpl implements CourseController{

    private final CourseService courseService;
    private final CourseDtoMapper courseDtoMapper;

    @Override
    @PostMapping("/register")
    public ResponseEntity<CourseDto> save(@RequestBody CourseDto courseDto) {
        return ResponseEntity.ok().body(
                courseDtoMapper.toCourseDto(courseService.save(courseDtoMapper.toCourse(courseDto)))
        );
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(
                courseDtoMapper.toCourseDto(courseService.findById(id).orElseThrow())
        );
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<CourseDto>> listAll() {
        return ResponseEntity.ok().body(
                courseService.listAll().stream().map(courseDtoMapper::toCourseDto).toList()
        );
    }


    @Override
    @GetMapping("/day/{dayOfWeek}")
    public ResponseEntity<List<CourseDto>> findByDayOfWeek(@PathVariable DayOfWeek dayOfWeek) {
        return ResponseEntity.ok().body(
                courseService.findByDayOfWeek(dayOfWeek).stream().map(courseDtoMapper::toCourseDto).toList()
        );
    }

    @Override
    @GetMapping("/name/{name}")
    public ResponseEntity<List<CourseDto>> findByName(@PathVariable String name) {
        return ResponseEntity.ok().body(
                courseService.findByName(name).stream().map(courseDtoMapper::toCourseDto).toList()
        );
    }
}

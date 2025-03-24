package com.kirtasth.springboot.menciones.app.mencionesappfinal.givenclass.infrastructure.controller;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.course.domain.CourseService;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.exception.domain.NotFoundException;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.givenclass.domain.GivenClassService;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.givenclass.infrastructure.rest.GivenClassDto;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.givenclass.infrastructure.rest.GivenClassDtoMapper;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.domain.ProfessorService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/given_class")
@AllArgsConstructor
public class GivenClassControllerImpl implements GivenClassController{

    private final GivenClassService givenClassService;
    private final ProfessorService professorService;
    private final CourseService courseService;
    private final GivenClassDtoMapper givenClassDtoMapper;

    @Override
    @PostMapping("/register")
    public ResponseEntity<GivenClassDto> register(@RequestBody GivenClassDto givenClassDto) {
        return ResponseEntity.ok().body(
                givenClassDtoMapper.toGivenClassDto(givenClassService.save(
                        givenClassDtoMapper.toGivenClass(givenClassDto,
                                professorService.findById(givenClassDto.getProfessorId()).orElseThrow(() ->
                                        new NotFoundException("Professor not found")),
                                courseService.findById(givenClassDto.getCourseId()).orElseThrow(() ->
                                        new NotFoundException("Course not found"))))
        ));
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<GivenClassDto>> listAll() {
        return ResponseEntity.ok().body(
                givenClassService.listAll().stream().map(
                        givenClassDtoMapper::toGivenClassDto
                ).toList()
        );
    }

    @Override
    @GetMapping("/{dayOfWeek}")
    public ResponseEntity<List<GivenClassDto>> listAllByDay(@PathVariable DayOfWeek dayOfWeek) {
        return ResponseEntity.ok().body(
                givenClassService.listAllByDayOfWeek(dayOfWeek).stream().map(
                        givenClassDtoMapper::toGivenClassDto
                ).toList()
        );
    }

    @Override
    @GetMapping("/{dayOfWeek}/{localTime}")
    public ResponseEntity<List<GivenClassDto>> listAllByDayAndHour(@PathVariable DayOfWeek dayOfWeek,
                                                                   @PathVariable @DateTimeFormat(pattern = "HH:mm:ss") LocalTime localTime) {
        return ResponseEntity.ok().body(
                givenClassService.listAllByDayOfWeekAndHour(dayOfWeek, localTime).stream().map(
                        givenClassDtoMapper::toGivenClassDto
                ).toList()
        );
    }
}

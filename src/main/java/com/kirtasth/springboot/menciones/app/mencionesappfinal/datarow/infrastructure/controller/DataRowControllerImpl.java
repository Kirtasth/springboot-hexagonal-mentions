package com.kirtasth.springboot.menciones.app.mencionesappfinal.datarow.infrastructure.controller;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.course.domain.CourseService;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.datarow.domain.DataRow;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.datarow.infrastructure.DataRowMapper;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.datarow.infrastructure.GoogleSheetsRepository;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.givenclass.domain.GivenClassService;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.domain.ProfessorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/data")
public class DataRowControllerImpl implements DataRowController{

    private final GoogleSheetsRepository googleSheetsRepository;
    private final DataRowMapper dataRowMapper;
    private final ProfessorService professorService;
    private final CourseService courseService;
    private final GivenClassService givenClassService;

    @Override
    @Transactional
    @GetMapping("/list")
    public ResponseEntity<List<DataRow>> getData() throws GeneralSecurityException, IOException {
        List<DataRow> data = googleSheetsRepository.getData();
        data.forEach(row -> {
            professorService.save(dataRowMapper.getProfessor(row));
            courseService.save(dataRowMapper.getCourse(row));
                });
        data.forEach(row -> {
            givenClassService.save(dataRowMapper.getGivenClass(
                    row,
                    professorService.findById(row.getProfessorId()).orElseThrow(),
                    courseService.findById(row.getCourseId()).orElseThrow()
            ));
        });

        return ResponseEntity.ok().body(
                data
        );
    }
}

package com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.infrastructure.controller;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.domain.ProfessorService;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.domain.model.ProfessorRole;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.infrastructure.rest.ProfessorDto;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.infrastructure.rest.ProfessorDtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/professors")
public class ProfessorControllerImpl implements ProfessorController{

    private ProfessorService professorService;

    private ProfessorDtoMapper professorDtoMapper;
    
    @GetMapping("/{role}")
    public ResponseEntity<List<ProfessorDto>> listByRole(@PathVariable ProfessorRole role){

        return ResponseEntity.ok().body(
                professorService.allProfessorByRole(role).stream().map(professorDtoMapper::toProfessorDto).toList());
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProfessorDto>> listAll(){
        List<ProfessorDto> list = professorService.findAll().stream().map(professorDtoMapper::toProfessorDto).toList();

        return ResponseEntity.ok(list);
    }

    @PostMapping("/register")
    public ResponseEntity<ProfessorDto> save(@RequestBody ProfessorDto professorDto) {
        return ResponseEntity.ok().body(
                professorDtoMapper.toProfessorDto(professorService.save(professorDtoMapper.toProfessor(professorDto))));
    }

}

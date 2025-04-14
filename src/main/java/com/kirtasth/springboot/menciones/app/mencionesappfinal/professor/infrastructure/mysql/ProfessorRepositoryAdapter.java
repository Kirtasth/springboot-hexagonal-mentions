package com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.infrastructure.mysql;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.domain.model.Professor;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.domain.ProfessorRepository;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.infrastructure.ProfessorEntity;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.infrastructure.ProfessorMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ProfessorRepositoryAdapter implements ProfessorRepository {


    private final ProfessorJpaRepository professorJpaRepository;
    private final ProfessorMapper professorMapper;


    @Override
    public Professor save(Professor professor) {
        ProfessorEntity professorEntityMapped = professorMapper.toProfessorEntity(professor);
        ProfessorEntity professorEntity = professorJpaRepository.save(professorEntityMapped);
        return professorMapper.toProfessor(professorEntity);
    }

    @Override
    public Optional<Professor> findById(Long id) {
        return professorJpaRepository.findById(id).map(professorMapper::toProfessor);
    }

    @Override
    public List<Professor> findAll() {
        return professorJpaRepository.findAll().stream().map(professorMapper::toProfessor).toList();
    }

    @Override
    public void deleteById(Long id) {
        professorJpaRepository.deleteById(id);
    }


}

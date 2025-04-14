package com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.application;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.domain.model.Professor;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.domain.ProfessorRepository;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.domain.model.ProfessorRole;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.domain.ProfessorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository professorRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Professor> findById(Long id) {
        return professorRepository.findById(id);
    }

    @Override
    @Transactional
    public Professor save(Professor professor) {

        return professorRepository.save(professor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Professor> allProfessorByRole(ProfessorRole professorRole) {

        List<Professor> listAll = professorRepository.findAll();

        return listAll.stream().filter(
                p -> p.getProfessorRole().equals(professorRole)).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

}

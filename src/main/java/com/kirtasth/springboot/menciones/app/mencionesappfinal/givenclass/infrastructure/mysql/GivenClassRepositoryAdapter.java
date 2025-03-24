package com.kirtasth.springboot.menciones.app.mencionesappfinal.givenclass.infrastructure.mysql;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.givenclass.domain.GivenClassRepository;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.givenclass.domain.model.GivenClass;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.givenclass.infrastructure.GivenClassEntity;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.givenclass.infrastructure.GivenClassMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class GivenClassRepositoryAdapter implements GivenClassRepository {

    private final GivenClassJpaRepository givenClassJpaRepository;
    private final GivenClassMapper givenClassMapper;

    @Override
    public GivenClass save(GivenClass givenClass) {
        GivenClassEntity givenClassEntity = givenClassJpaRepository.save(givenClassMapper.toGivenClassEntity(givenClass));
        return givenClassMapper.toGivenClass(givenClassEntity);
    }

    @Override
    public Optional<GivenClass> findById(Long id) {
        return givenClassJpaRepository.findById(id).map(givenClassMapper::toGivenClass);
    }

    @Override
    public List<GivenClass> listAll() {
        return givenClassJpaRepository.findAll().stream().map(givenClassMapper::toGivenClass).toList();
    }

    @Override
    public List<GivenClass> listAllByDayOfWeek(DayOfWeek dayOfWeek) {
        return givenClassJpaRepository.findAll().stream().filter(gc ->
                gc.getCourseEntity().getDayOfWeek().equals(dayOfWeek)).map(givenClassMapper::toGivenClass).toList();
    }

    @Override
    public List<GivenClass> listAllByDayOfWeekAndHour(DayOfWeek dayOfWeek, LocalTime localTime) {
        return givenClassJpaRepository.findAll().stream().filter(gc ->
                gc.getCourseEntity().getDayOfWeek().equals(dayOfWeek)
                        && gc.getCourseEntity().getStartHour().equals(localTime))
                .map(givenClassMapper::toGivenClass).toList();
    }
}

package com.kirtasth.springboot.menciones.app.mencionesappfinal.givenclass.application;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.exception.domain.GivenClassAlreadyRegistered;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.givenclass.domain.GivenClassRepository;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.givenclass.domain.GivenClassService;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.givenclass.domain.model.GivenClass;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GivenClassServiceImpl implements GivenClassService {

    private final GivenClassRepository givenClassRepository;

    @Override
    @Transactional
    public GivenClass save(GivenClass givenClass) {

        Optional<GivenClass> givenClassDB = givenClassRepository.findById(givenClass.getId());
        if (givenClassDB.isPresent()){
            throw new GivenClassAlreadyRegistered("There is a given class with the same id.");
        } else {
            return givenClassRepository.save(givenClass);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GivenClass> findById(Long id) {
        return givenClassRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GivenClass> listAll() {
        return givenClassRepository.listAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<GivenClass> listAllByDayOfWeek(DayOfWeek dayOfWeek) {
        return givenClassRepository.listAllByDayOfWeek(dayOfWeek);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GivenClass> listAllByDayOfWeekAndHour(DayOfWeek dayOfWeek, LocalTime localTime) {
        return givenClassRepository.listAllByDayOfWeekAndHour(dayOfWeek, localTime);
    }
}

package com.kirtasth.springboot.menciones.app.mencionesappfinal.givenclass.domain;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.givenclass.domain.model.GivenClass;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface GivenClassRepository {

    GivenClass save(GivenClass givenClass);

    Optional<GivenClass> findById(Long id);

    List<GivenClass> listAll();

    List<GivenClass> listAllByDayOfWeek(DayOfWeek dayOfWeek);

    List<GivenClass> listAllByDayOfWeekAndHour(DayOfWeek dayOfWeek, LocalTime localTime);

}

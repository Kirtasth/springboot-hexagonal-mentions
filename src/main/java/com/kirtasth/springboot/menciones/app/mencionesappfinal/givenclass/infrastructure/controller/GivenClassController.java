package com.kirtasth.springboot.menciones.app.mencionesappfinal.givenclass.infrastructure.controller;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.givenclass.infrastructure.rest.GivenClassDto;
import org.springframework.http.ResponseEntity;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public interface GivenClassController {

    ResponseEntity<GivenClassDto> register(GivenClassDto givenClassDto);

    ResponseEntity<List<GivenClassDto>> listAll();

    ResponseEntity<List<GivenClassDto>> listAllByDay(DayOfWeek dayOfWeek);

    ResponseEntity<List<GivenClassDto>> listAllByDayAndHour(DayOfWeek dayOfWeek, LocalTime localTime);
}

package com.kirtasth.springboot.menciones.app.mencionesappfinal.datarow.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;


@AllArgsConstructor
@Getter
@Setter
public class DataRow {

    private Long givenClassId;

    private Long professorId;

    private String professorName;

    private Long courseId;

    private LocalDate courseStart;

    private String courseName;

    private DayOfWeek dayOfWeek;

    private LocalTime startHour;

    private LocalTime endHour;

    private String meetLink;

    private String materialLink;


}

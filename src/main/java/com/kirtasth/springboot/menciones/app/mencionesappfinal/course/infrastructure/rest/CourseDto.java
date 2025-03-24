package com.kirtasth.springboot.menciones.app.mencionesappfinal.course.infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class CourseDto {

    private Long id;

    private String name;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate courseStart;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startHour;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime endHour;

    @JsonCreator
    public CourseDto(@JsonProperty("id")Long id,
                     @JsonProperty("name")String name,
                     @JsonProperty("courseStart") LocalDate courseStart,
                     @JsonProperty("dayOfWeek") DayOfWeek dayOfWeek,
                     @JsonProperty("startHour")LocalTime startHour,
                     @JsonProperty("endHour")LocalTime endHour) {
        this.id = id;
        this.name = name;
        this.courseStart = courseStart;
        this.dayOfWeek = dayOfWeek;
        this.startHour = startHour;
        this.endHour = endHour;
    }
}

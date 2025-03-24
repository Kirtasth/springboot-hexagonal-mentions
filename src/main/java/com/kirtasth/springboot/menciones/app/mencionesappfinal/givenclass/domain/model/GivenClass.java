package com.kirtasth.springboot.menciones.app.mencionesappfinal.givenclass.domain.model;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.course.domain.model.Course;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.domain.model.Professor;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class GivenClass {

    @NotNull
    private Long id;

    @NotNull
    private String meetLink;

    @NotNull
    private String materialLink;

    @NotNull
    private Professor professor;

    @NotNull
    private Course course;
}

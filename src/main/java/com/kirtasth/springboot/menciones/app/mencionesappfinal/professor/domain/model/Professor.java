package com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.domain.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class Professor {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    @NotNull
    private String discordName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProfessorRole professorRole;
}

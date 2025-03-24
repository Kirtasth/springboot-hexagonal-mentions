package com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.infrastructure.rest;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.domain.model.ProfessorRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class ProfessorDto {

    private Long id;

    private String name;

    private String lastName;

    private String discordName;

    @Enumerated(EnumType.STRING)
    private ProfessorRole professorRole;

    @JsonCreator
    public ProfessorDto(@JsonProperty("id") Long id,
                        @JsonProperty("name")String name,
                        @JsonProperty("lastName")String lastName,
                        @JsonProperty("discordName")String discordName,
                        @JsonProperty("professorRole")ProfessorRole professorRole) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.discordName = discordName;
        this.professorRole = professorRole;
    }
}

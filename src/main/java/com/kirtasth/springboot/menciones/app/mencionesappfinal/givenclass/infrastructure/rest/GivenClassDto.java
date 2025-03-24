package com.kirtasth.springboot.menciones.app.mencionesappfinal.givenclass.infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class GivenClassDto {

    @NotNull
    private Long id;

    @NotNull
    private String meetLink;

    @NotNull
    private String materialLink;

    @NotNull
    private Long professorId;

    @NotNull
    private Long courseId;

    @JsonCreator
    public GivenClassDto(
            @JsonProperty("id") Long id,
            @JsonProperty("meetLink") String meetLink,
            @JsonProperty("materialLink") String materialLink,
            @JsonProperty("professorId") Long professorId,
            @JsonProperty("courseId") Long courseId) {
        this.id = id;
        this.meetLink = meetLink;
        this.materialLink = materialLink;
        this.professorId = professorId;
        this.courseId = courseId;
    }

}

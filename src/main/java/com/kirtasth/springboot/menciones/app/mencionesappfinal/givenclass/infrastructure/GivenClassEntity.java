package com.kirtasth.springboot.menciones.app.mencionesappfinal.givenclass.infrastructure;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.course.infrastructure.CourseEntity;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.infrastructure.ProfessorEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "given_classes")
public class GivenClassEntity {

    @Id
    @Column(name = "given_class_id")
    private Long id;

    @NotNull
    private String meetLink;

    @NotNull
    private String materialLink;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "professor_id")
    private ProfessorEntity professorEntity;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity courseEntity;


    @Column(name = "created_at")
    private LocalDateTime createdAt;


    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    private void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
}

package com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.infrastructure;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.domain.model.ProfessorRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "professors")
public class ProfessorEntity {

    @Column(name = "professor_id")
    @Id
    private Long id;

    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "discord_name", unique = true)
    private String discordName;

    @Enumerated(EnumType.STRING)
    private ProfessorRole professorRole;

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

package com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.infrastructure;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.professor.domain.model.ProfessorRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;


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

    @Column(name = "discord_name")
    private String discordName;

    @Enumerated(EnumType.STRING)
    private ProfessorRole professorRole;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;

}

package com.kirtasth.springboot.menciones.app.mencionesappfinal.course.infrastructure;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class CourseEntity {

    @Column(name = "course_id")
    @Id
    private Long id;

    private String name;

    @Column(name = "course_start")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate courseStart;

    @Column(name = "day_of_week")
    private DayOfWeek dayOfWeek;

    @Column(name = "start_hour")
    private LocalTime startHour;

    @Column(name = "end_hour")
    private LocalTime endHour;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;

}

package com.assignment.CourseRegistration.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvailableTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startTime;
    private int durationMinutes; // 30 또는 60
    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;
}

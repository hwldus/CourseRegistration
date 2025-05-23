package com.assignment.CourseRegistration.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegistrationRequestDTO {
    private Long studentId;
    private Long tutorId;
    private LocalDateTime startTime;
    private int durationMinutes;
}

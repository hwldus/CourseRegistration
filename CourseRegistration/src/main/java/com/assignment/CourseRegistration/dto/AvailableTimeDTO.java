package com.assignment.CourseRegistration.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AvailableTimeDTO {
    private LocalDateTime startTime;
    private int durationMinutes;
}

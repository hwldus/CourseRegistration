package com.assignment.CourseRegistration.dto;

import com.assignment.CourseRegistration.model.AvailableTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AvailableTimeResponseDTO {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int durationMinutes;
    private Long tutorId;
    public static AvailableTimeResponseDTO fromEntity(AvailableTime time) {
        return AvailableTimeResponseDTO.builder()
                .id(time.getId())
                .startTime(time.getStartTime())
                .endTime(time.getEndTime())
                .durationMinutes(time.getDurationMinutes())
                .id(time.getTutor().getId())
                .build();
    }
}

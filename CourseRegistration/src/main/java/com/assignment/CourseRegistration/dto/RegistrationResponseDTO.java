package com.assignment.CourseRegistration.dto;

import com.assignment.CourseRegistration.model.ClassRegistration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponseDTO {
    private Long classId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int durationMinutes;
    private Long tutorId;
    private String tutorName;

    public static RegistrationResponseDTO fromEntity(ClassRegistration registration) {
        return RegistrationResponseDTO.builder()
                .classId(registration.getId())
                .startTime(registration.getAvailableTime().getStartTime())
                .endTime(registration.getAvailableTime().getEndTime())
                .durationMinutes(registration.getAvailableTime().getDurationMinutes())
                .tutorId(registration.getAvailableTime().getTutor().getId())
                .tutorName(registration.getAvailableTime().getTutor().getName())
                .build();
    }
}

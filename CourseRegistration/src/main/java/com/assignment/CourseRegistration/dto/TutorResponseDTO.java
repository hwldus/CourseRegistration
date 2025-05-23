package com.assignment.CourseRegistration.dto;

import com.assignment.CourseRegistration.model.Tutor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TutorResponseDTO {
    private Long id;
    private String name;
    public static TutorResponseDTO fromEntity(Tutor tutor) {
        return TutorResponseDTO.builder()
                .id(tutor.getId())
                .name(tutor.getName())
                .build();
    }
}

package com.assignment.CourseRegistration.service;

import com.assignment.CourseRegistration.dto.TutorResponseDTO;
import com.assignment.CourseRegistration.model.AvailableTime;
import com.assignment.CourseRegistration.repository.AvailableTimeRepository;
import com.assignment.CourseRegistration.repository.ClassRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final AvailableTimeRepository availableTimeRepository;
    private final ClassRegistrationRepository classRegistrationRepository;

    public List<AvailableTime> getAvailableTimes(LocalDateTime startTime, LocalDateTime endTime, int durationMinutes) {
        List<AvailableTime> list = availableTimeRepository.findByStartTimeBetweenAndDurationMinutes(startTime, endTime, durationMinutes);
        return list.stream()
                .filter(time -> !classRegistrationRepository.existsByAvailableTime(time))
                .collect(Collectors.toList());
    }

    public List<TutorResponseDTO> getAvailableTutors(LocalDateTime startTime, LocalDateTime endTime, int durationMinutes) {
        List<AvailableTime> list = availableTimeRepository.findByStartTimeBetweenAndDurationMinutes(startTime, endTime, durationMinutes);
        return list.stream()
                .filter(time -> !classRegistrationRepository.existsByAvailableTime(time))
                .map(AvailableTime::getTutor)
                .distinct()
                .map(TutorResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }
}

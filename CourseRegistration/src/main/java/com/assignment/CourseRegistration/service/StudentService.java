package com.assignment.CourseRegistration.service;

import com.assignment.CourseRegistration.dto.RegistrationRequestDTO;
import com.assignment.CourseRegistration.dto.TutorResponseDTO;
import com.assignment.CourseRegistration.model.AvailableTime;
import com.assignment.CourseRegistration.model.ClassRegistration;
import com.assignment.CourseRegistration.model.Student;
import com.assignment.CourseRegistration.model.Tutor;
import com.assignment.CourseRegistration.repository.AvailableTimeRepository;
import com.assignment.CourseRegistration.repository.ClassRegistrationRepository;
import com.assignment.CourseRegistration.repository.StudentRepository;
import com.assignment.CourseRegistration.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final AvailableTimeRepository availableTimeRepository;
    private final ClassRegistrationRepository classRegistrationRepository;
    private final StudentRepository studentRepository;
    private final TutorRepository tutorRepository;

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

    @Transactional
    public ClassRegistration createClass(RegistrationRequestDTO requestDTO) {
        Student student = studentRepository.findById(requestDTO.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("학생이 존재하지 않습니다."));
        Tutor tutor = tutorRepository.findById(requestDTO.getTutorId())
                .orElseThrow(() -> new IllegalArgumentException("튜터가 존재하지 않습니다."));
        AvailableTime time = availableTimeRepository.findByTutorAndStartTimeAndDurationMinutes(tutor, requestDTO.getStartTime(), requestDTO.getDurationMinutes())
                .orElseThrow(() -> new IllegalArgumentException("해당 시간에 수업 가능한 튜터가 존재하지 않습니다."));
        if(classRegistrationRepository.existsByAvailableTime(time)) {
            throw new IllegalArgumentException("이미 예약된 수업입니다.");
        }
        ClassRegistration registration = ClassRegistration.builder()
                .student(student)
                .availableTime(time)
                .build();
        return classRegistrationRepository.save(registration);
    }
}

package com.assignment.CourseRegistration.service;

import com.assignment.CourseRegistration.model.AvailableTime;
import com.assignment.CourseRegistration.model.Tutor;
import com.assignment.CourseRegistration.repository.AvailableTimeRepository;
import com.assignment.CourseRegistration.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TutorService {
    private final TutorRepository tutorRepository;
    private final AvailableTimeRepository availableTimeRepository;

    @Transactional
    public AvailableTime createAvailableTime(Long tutorId, LocalDateTime startTime, int durationMinutes) {
        int minute = startTime.getMinute();
        if (minute != 0 && minute != 30) {
            throw new IllegalArgumentException("수업 시작 시간은 정각 또는 30분이어야 합니다.");
        }
        if (durationMinutes != 30 && durationMinutes != 60) {
            throw new IllegalArgumentException("수업 길이는 30분 또는 60분만 가능합니다.");
        }
        Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() -> new IllegalArgumentException("튜터가 존재하지 않습니다."));
        boolean alreadyExists = availableTimeRepository.existsByTutorIdAndStartTime(tutorId, startTime);
        if(alreadyExists)
            throw new IllegalArgumentException("해당 튜터는 이미 이 시간에 수업이 등록되어 있습니다.");
        AvailableTime availableTime = AvailableTime.builder()
                .startTime(startTime)
                .durationMinutes(durationMinutes)
                .tutor(tutor)
                .build();
        return availableTimeRepository.save(availableTime);
    }

    @Transactional
    public void deleteAvailableTime(Long tutorId, Long availableTimeId) {
        AvailableTime availableTime = availableTimeRepository.findById(availableTimeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 시간 정보가 존재하지 않습니다."));
        if(!availableTime.getTutor().getId().equals(tutorId)) {
            throw new IllegalArgumentException("해당 튜터의 수업 가능 시간만 삭제할 수 있습니다.");
        }
        availableTimeRepository.delete(availableTime);
    }
}

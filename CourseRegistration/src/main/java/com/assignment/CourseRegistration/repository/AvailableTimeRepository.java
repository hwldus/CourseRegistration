package com.assignment.CourseRegistration.repository;

import com.assignment.CourseRegistration.model.AvailableTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AvailableTimeRepository extends JpaRepository<AvailableTime, Long> {
    List<AvailableTime> findByTutorId(Long tutorId); //튜터의 가능한 시간 목록 조회
    boolean existsByTutorIdAndStartTime(Long tutorId, LocalDateTime startTime);
}

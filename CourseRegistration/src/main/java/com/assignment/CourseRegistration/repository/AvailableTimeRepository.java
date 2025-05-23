package com.assignment.CourseRegistration.repository;

import com.assignment.CourseRegistration.model.AvailableTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AvailableTimeRepository extends JpaRepository<AvailableTime, Long> {
    List<AvailableTime> findByTutorId(Long tutorId); //튜터의 가능한 시간 목록 조회
    boolean existsByTutorIdAndStartTimeLessThanAndEndTimeGreaterThan(
            Long tutorId, LocalDateTime newEndTime, LocalDateTime newStartTime
    ); // 튜터의 겹치는 시간 확인
    boolean existsByTutorIdAndStartTime(Long tutorId, LocalDateTime startTime); //튜터의 시작하는 시간이 같은 경우 확인

    List<AvailableTime> findByStartTimeBetweenAndDurationMinutes(LocalDateTime startTime, LocalDateTime endTime, int durationMinutes);// 조회

}

package com.assignment.CourseRegistration.repository;

import com.assignment.CourseRegistration.model.AvailableTime;
import com.assignment.CourseRegistration.model.ClassRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRegistrationRepository extends JpaRepository<ClassRegistration, Long> {
    boolean existsByAvailableTime(AvailableTime availableTime);
}

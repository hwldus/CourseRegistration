package com.assignment.CourseRegistration.repository;

import com.assignment.CourseRegistration.model.AvailableTime;
import com.assignment.CourseRegistration.model.ClassRegistration;
import com.assignment.CourseRegistration.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRegistrationRepository extends JpaRepository<ClassRegistration, Long> {
    boolean existsByAvailableTime(AvailableTime availableTime);
    List<ClassRegistration> findByStudent(Student student);
}

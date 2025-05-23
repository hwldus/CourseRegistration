package com.assignment.CourseRegistration.repository;

import com.assignment.CourseRegistration.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}

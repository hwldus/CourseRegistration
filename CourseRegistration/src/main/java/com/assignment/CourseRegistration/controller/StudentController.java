package com.assignment.CourseRegistration.controller;

import com.assignment.CourseRegistration.dto.AvailableTimeResponseDTO;
import com.assignment.CourseRegistration.dto.RegistrationRequestDTO;
import com.assignment.CourseRegistration.dto.TutorResponseDTO;
import com.assignment.CourseRegistration.model.AvailableTime;
import com.assignment.CourseRegistration.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/availableTimes")
    public ResponseEntity<List<AvailableTimeResponseDTO>> getAvailableTimes(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
                                                                            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime,
                                                                            @RequestParam("duration") int durationMinutes) {
        List<AvailableTime> availableTimes = studentService.getAvailableTimes(startTime, endTime, durationMinutes);
        List<AvailableTimeResponseDTO> response = availableTimes.stream()
                .map(AvailableTimeResponseDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/availableTutors")
    public ResponseEntity<List<TutorResponseDTO>> getAvailableTutors(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
                                                                     @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime,
                                                                     @RequestParam("duration") int durationMinutes) {
        List<TutorResponseDTO> tutors = studentService.getAvailableTutors(startTime, endTime, durationMinutes);
        return ResponseEntity.ok(tutors);
    }

    @PostMapping("/register")
    public ResponseEntity<String> createClass(@RequestBody RegistrationRequestDTO requestDTO) {
        studentService.createClass(requestDTO);
        return ResponseEntity.ok("수강신청되었습니다.");
    }
}

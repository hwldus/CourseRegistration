package com.assignment.CourseRegistration.controller;

import com.assignment.CourseRegistration.dto.AvailableTimeResponseDTO;
import com.assignment.CourseRegistration.dto.RegistrationRequestDTO;
import com.assignment.CourseRegistration.dto.RegistrationResponseDTO;
import com.assignment.CourseRegistration.dto.TutorResponseDTO;
import com.assignment.CourseRegistration.model.AvailableTime;
import com.assignment.CourseRegistration.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
@Tag(name="학생 API", description = "학생의 수강신청 API")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/availableTimes")
    @Operation(summary = "수업 가능 시간대 조회", description = "기간 & 수업 길이로 현재 수업 가능한 시간대 조회")
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
    @Operation(summary = "수업 가능한 튜터 조회", description = "시간대 & 수업 길이로 수업 가능한 튜터 조회")
    public ResponseEntity<List<TutorResponseDTO>> getAvailableTutors(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
                                                                     @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime,
                                                                     @RequestParam("duration") int durationMinutes) {
        List<TutorResponseDTO> tutors = studentService.getAvailableTutors(startTime, endTime, durationMinutes);
        return ResponseEntity.ok(tutors);
    }

    @PostMapping("/register")
    @Operation(summary = "수강 신청", description = "시간대, 수업 길이, 튜터로 새로운 수업 신청")
    public ResponseEntity<String> createClass(@RequestBody RegistrationRequestDTO requestDTO) {
        studentService.createClass(requestDTO);
        return ResponseEntity.ok("수강신청되었습니다.");
    }

    @GetMapping("/classes")
    @Operation(summary = "신청한 수업 조회", description = "학생이 신청 완료한 수업을 조회")
    public ResponseEntity<List<RegistrationResponseDTO>> getRegisteredClasses(@RequestParam Long studentId) {
        return ResponseEntity.ok(studentService.getRegistration(studentId));
    }
}

package com.assignment.CourseRegistration.controller;

import com.assignment.CourseRegistration.dto.AvailableTimeDTO;
import com.assignment.CourseRegistration.service.TutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutors")
@RequiredArgsConstructor
public class TutorController {
    private final TutorService tutorService;
    @PostMapping("/{tutorId}/availableTime")
    public ResponseEntity<String> createAvailableTime(@PathVariable Long tutorId,
                                                      @RequestBody AvailableTimeDTO request) {
        tutorService.createAvailableTime(tutorId, request.getStartTime(), request.getDurationMinutes());
        return ResponseEntity.ok("수업 가능한 시간이 생성되었습니다.");
    }
    @DeleteMapping("/{tutorId}/availableTime/{availableTimeId}")
    public ResponseEntity<String> deleteAvailableTime(@PathVariable Long tutorId,
                                                      @PathVariable Long availableTimeId) {
        tutorService.deleteAvailableTime(tutorId, availableTimeId);
        return ResponseEntity.ok("수업 가능한 시간이 삭제되었습니다.");
    }
}

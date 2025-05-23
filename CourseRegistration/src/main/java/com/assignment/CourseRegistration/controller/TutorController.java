package com.assignment.CourseRegistration.controller;

import com.assignment.CourseRegistration.dto.AvailableTimeDTO;
import com.assignment.CourseRegistration.service.TutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutors")
@Tag(name="튜터 API", description = "튜터의 시간 API")
@RequiredArgsConstructor
public class TutorController {
    private final TutorService tutorService;
    @PostMapping("/{tutorId}/availableTime")
    @Operation(summary = "수업 생성", description = "튜터가 수업 가능한 시간 생성(30분 또는 60분)")
    public ResponseEntity<String> createAvailableTime(@PathVariable Long tutorId,
                                                      @RequestBody AvailableTimeDTO request) {
        tutorService.createAvailableTime(tutorId, request.getStartTime(), request.getDurationMinutes());
        return ResponseEntity.ok("수업 가능한 시간이 생성되었습니다.");
    }
    @DeleteMapping("/{tutorId}/availableTime/{availableTimeId}")
    @Operation(summary = "수업 삭제", description = "튜터가 수업 삭제")
    public ResponseEntity<String> deleteAvailableTime(@PathVariable Long tutorId,
                                                      @PathVariable Long availableTimeId) {
        tutorService.deleteAvailableTime(tutorId, availableTimeId);
        return ResponseEntity.ok("수업 가능한 시간이 삭제되었습니다.");
    }
}

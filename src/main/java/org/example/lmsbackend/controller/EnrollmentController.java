package org.example.lmsbackend.controller;

import org.example.lmsbackend.dto.EnrollmentCreateDTO;
import org.example.lmsbackend.dto.EnrollmentResponseDTO;
import org.example.lmsbackend.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class EnrollmentController {
    private final EnrollmentService enrollmentService;

    EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping("/api/enrollments")
    ResponseEntity<List<EnrollmentResponseDTO>> getEnrollments() {
        return ResponseEntity.ok(enrollmentService.getEnrollments());
    }

    @PostMapping("/api/enrollments")
    ResponseEntity<EnrollmentResponseDTO> addEnrollment(@RequestBody EnrollmentCreateDTO dto){
        return ResponseEntity.ok(enrollmentService.addEnrollment(dto));
    }
}

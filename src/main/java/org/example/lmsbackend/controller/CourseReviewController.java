package org.example.lmsbackend.controller;

import org.example.lmsbackend.dto.CourseReviewCreateDTO;
import org.example.lmsbackend.dto.CourseReviewResponseDTO;
import org.example.lmsbackend.service.CourseReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class CourseReviewController {

    private final CourseReviewService courseReviewService;

    public CourseReviewController(CourseReviewService courseReviewService) {
        this.courseReviewService = courseReviewService;
    }

    @PostMapping("/api/reviews")
    ResponseEntity<CourseReviewResponseDTO> addReview(@RequestBody CourseReviewCreateDTO dto){
        return ResponseEntity.ok(courseReviewService.addReview(dto));
    }
}

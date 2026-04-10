package org.example.lmsbackend.dto;

public record CourseReviewCreateDTO(
        String reviewText,
        Double rating,
        Long userId,
        Long courseId
) {
}

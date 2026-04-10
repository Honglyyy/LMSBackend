package org.example.lmsbackend.dto;

public record CourseReviewResponseDTO(
        Long reviewId,
        String reviewText,
        Double rating,
        String username,
        String courseTitle
) {
}

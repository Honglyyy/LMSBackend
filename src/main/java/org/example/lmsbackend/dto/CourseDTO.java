package org.example.lmsbackend.dto;

public record CourseDTO(
        Long courseId,
        String title,
        String description,
        double price,
        String overallDuration,
        String coverDir,
        String instructor
) {
}

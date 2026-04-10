package org.example.lmsbackend.dto;

import org.example.lmsbackend.model.Sections;

import java.util.List;

public record CourseResponseDTO(
        Long courseId,
        String title,
        String description,
        double price,
        String overallDuration,
        String coverDir,
        String instructor,
        List<String> categories,
        Double rating
) {
}

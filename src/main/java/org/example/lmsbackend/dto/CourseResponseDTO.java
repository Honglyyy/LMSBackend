package org.example.lmsbackend.dto;

import java.util.List;

public record CourseResponseDTO(
        Long courseId,
        String title,
        String description,
        double price,
        String instructor,
        List<String> categories
) {
}

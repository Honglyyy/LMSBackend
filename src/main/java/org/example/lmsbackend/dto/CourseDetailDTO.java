package org.example.lmsbackend.dto;

import java.util.List;

public record CourseDetailDTO(
        Long courseId,
        String title,
        String description,
        double price,
        String overallDuration,
        String coverDir,
        String instructor,
        List<String> categories,
        List<SectionDTO> sections
) {
}

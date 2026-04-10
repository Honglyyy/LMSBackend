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
        Long sectionCount,
        Double rating,
        List<String> categories,
        List<SectionDetailDTO> sections,
        List<CourseReviewResponseDTO> reviews
) {
}

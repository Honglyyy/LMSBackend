package org.example.lmsbackend.dto;

import java.util.List;

public record SectionDetailDTO(
        Long sectionId,
        String title,
        String duration,
        Long lessonCount,
        List<LessonDetailDTO> lessons
) {
}

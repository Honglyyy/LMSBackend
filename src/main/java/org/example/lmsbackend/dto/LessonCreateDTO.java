package org.example.lmsbackend.dto;

public record LessonCreateDTO(
        String title,
        String videoDir,
        Long sectionId
) {
}

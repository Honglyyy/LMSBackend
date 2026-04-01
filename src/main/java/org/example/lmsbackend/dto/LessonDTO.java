package org.example.lmsbackend.dto;

public record LessonDTO(
        Long lessonId,
        String title,
        String videoDir
) {
}

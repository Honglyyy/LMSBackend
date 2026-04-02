package org.example.lmsbackend.dto;

import java.util.List;

public record LessonDetailDTO(
        Long lessonId,
        String title,
        String videoDir,
        List<QuizDetailDTO> quiz
) {
}

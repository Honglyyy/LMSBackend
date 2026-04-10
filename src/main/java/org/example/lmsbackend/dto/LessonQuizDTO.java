package org.example.lmsbackend.dto;

import java.util.List;

public record LessonQuizDTO(
        Long lessonId,
        String title,
        String videoDir,
        List<QuizDetailDTO> quizz
) {
}

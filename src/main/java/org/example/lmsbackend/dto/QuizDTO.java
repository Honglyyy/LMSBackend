package org.example.lmsbackend.dto;

public record QuizDTO(
        Long quizId,
        String title,
        double totalPoints
) {
}

package org.example.lmsbackend.dto;

public record QuizDetailDTO(
        Long quizId,
        String title,
        double totalPoints
) {
}

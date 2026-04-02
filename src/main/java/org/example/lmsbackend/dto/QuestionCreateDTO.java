package org.example.lmsbackend.dto;

public record QuestionCreateDTO(
        String questionText,
        Long quizId
) {
}

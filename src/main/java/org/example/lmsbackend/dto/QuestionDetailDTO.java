package org.example.lmsbackend.dto;

public record QuestionDetailDTO(
        Long questionId,
        String questionText,
        Long quizId
) {
}

package org.example.lmsbackend.dto;

public record AnswerResponseDTO(
        Long answerId,
        String answerText,
        Boolean isCorrect,
        Long questionId
) {
}

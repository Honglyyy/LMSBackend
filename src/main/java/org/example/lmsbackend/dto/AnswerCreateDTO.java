package org.example.lmsbackend.dto;

public record AnswerCreateDTO(
        String answerText,
        Boolean isCorrect,
        Long questionId
) {
}

package org.example.lmsbackend.dto;

public record QuizCreateDTO(
        String title,
        double totalPoints,
        Long lessonId
) {
}

package org.example.lmsbackend.dto;

public record SectionCreateDTO(
        String title,
        String duration,
        Long courseId
) {
}

package org.example.lmsbackend.dto;

import org.springframework.stereotype.Component;

import java.util.List;

public record CourseCreateDTO(
        String title,
        String description,
        double price,
        Long instructorId,
        List<Long> categoryId
) {
}

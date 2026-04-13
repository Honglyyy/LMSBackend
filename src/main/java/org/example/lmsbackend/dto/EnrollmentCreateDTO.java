package org.example.lmsbackend.dto;

import java.util.List;

public record EnrollmentCreateDTO(
        Long userId,
        Long courseId,
        Boolean isPaid
) {
}

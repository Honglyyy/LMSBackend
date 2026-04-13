package org.example.lmsbackend.dto;

import java.time.LocalDateTime;
import java.util.List;

public record EnrollmentResponseDTO(
        Long enrollmentId,
        Long userId,
        List<Long> courses,
        Boolean isPaid,
        LocalDateTime enrollmentDate
) {
}

package org.example.lmsbackend.dto;

import java.time.LocalDateTime;
import java.util.List;

public record EnrollmentUserDTO(
        Long enrollmentId,
        List<String> courses,
        Boolean isPaid,
        LocalDateTime enrolledAt
) {
}

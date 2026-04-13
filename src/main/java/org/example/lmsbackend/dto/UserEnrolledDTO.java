package org.example.lmsbackend.dto;

import java.util.List;

public record UserEnrolledDTO(
        Long userId,
        String username,
        String email,
        List<EnrollmentUserDTO> enrollments
) {
}

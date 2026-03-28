package org.example.lmsbackend.dto;

import org.example.lmsbackend.model.Roles;

import java.time.LocalDateTime;

public record UserResponseDTO(
        Long userId,
        String username,
        String role
) {
}

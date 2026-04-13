package org.example.lmsbackend.dto;

import java.time.LocalDateTime;

public record UserCreateDTO(
        String username,
        String email,
        String password,
        Long roleId
) {
}

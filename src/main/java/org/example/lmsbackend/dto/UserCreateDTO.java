package org.example.lmsbackend.dto;

import java.time.LocalDateTime;

public record UserCreateDTO(
        String username,
        String password,
        Long roleId
) {
}

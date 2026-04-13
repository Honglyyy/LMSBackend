package org.example.lmsbackend.mapper;

import org.example.lmsbackend.dto.UserCreateDTO;
import org.example.lmsbackend.dto.UserResponseDTO;
import org.example.lmsbackend.model.Courses;
import org.example.lmsbackend.model.Roles;
import org.example.lmsbackend.model.Users;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final Users users;

    public UserMapper(Users users) {
        this.users = users;
    }

    public Users toEntity(
            UserCreateDTO dto,
            Roles role
    ){
        Users user = new Users();
        user.setUsername(dto.username());
        user.setPassword(dto.password());
        user.setRole(role);
        return user;
    }

    public UserResponseDTO toDTO(Users user){

        return new UserResponseDTO(
                user.getUserId(),
                user.getEmail(),
                user.getUsername(),
                user.getRole().getRole()
        );
    }

    public void updateEntity(
            Users user,
            UserCreateDTO dto
    ){
        user.setUsername(dto.username());
        user.setPassword(dto.password());
    }
}

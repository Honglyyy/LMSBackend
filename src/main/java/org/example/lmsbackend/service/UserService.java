package org.example.lmsbackend.service;

import org.example.lmsbackend.dto.EnrollmentUserDTO;
import org.example.lmsbackend.dto.UserCreateDTO;
import org.example.lmsbackend.dto.UserEnrolledDTO;
import org.example.lmsbackend.dto.UserResponseDTO;
import org.example.lmsbackend.mapper.UserMapper;
import org.example.lmsbackend.model.Roles;
import org.example.lmsbackend.model.Users;
import org.example.lmsbackend.repository.EnrollmentRepository;
import org.example.lmsbackend.repository.RolesRepository;
import org.example.lmsbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RolesRepository rolesRepository;
    private final EnrollmentRepository enrollmentRepository;

    public UserService(UserRepository userRepository, UserMapper userMapper, RolesRepository rolesRepository, EnrollmentRepository enrollmentRepository) {
        this.userMapper = userMapper;
        this.rolesRepository = rolesRepository;
        this.userRepository = userRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .toList();
    }

    public UserEnrolledDTO getUser(Long id){
        List<EnrollmentUserDTO> enrollmentUserDTOS = enrollmentRepository.findByUser_UserId(id)
                .stream()
                .map(enrollment -> new EnrollmentUserDTO(
                        enrollment.getEnrollmentId(),
                        List.of(enrollment.getCourse().getTitle()),
                        enrollment.getIsPaid(),
                        enrollment.getEnrolledAt()
                ))
                .toList();

        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));

        return new UserEnrolledDTO(
                user.getUserId(),
                user.getUsername(),
                enrollmentUserDTOS
        );
    }

    public UserResponseDTO addUser(UserCreateDTO dto){
        Roles role = rolesRepository.findById(dto.roleId())
                .orElseThrow(() -> new RuntimeException("Role with id " + dto.roleId() + " not found"));

        Users user = userMapper.toEntity(dto,role);

        return userMapper.toDTO(userRepository.save(user));
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public UserResponseDTO updateUser(Long id, UserCreateDTO dto){
        Users user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User id " + id +" not found!!"));

        userMapper.updateEntity(user,dto);
         return userMapper.toDTO(userRepository.save(user));
    }

}

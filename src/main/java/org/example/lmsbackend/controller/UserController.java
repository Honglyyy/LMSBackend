package org.example.lmsbackend.controller;

import jakarta.annotation.Nullable;
import org.example.lmsbackend.dto.UserCreateDTO;
import org.example.lmsbackend.dto.UserResponseDTO;
import org.example.lmsbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/api/users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping("api/users")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserCreateDTO dto){
        return new ResponseEntity<>(userService.createUser(dto),HttpStatus.CREATED);
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("User id " + id + " has now been deleted!!!",HttpStatus.OK);
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long id
            ,@RequestBody UserCreateDTO dto){
        return new ResponseEntity<>(userService.updateUser(id,dto),HttpStatus.OK);
    }
}

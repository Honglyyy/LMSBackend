package org.example.lmsbackend.controller;

import org.example.lmsbackend.model.Roles;
import org.example.lmsbackend.service.RolesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RolesController {


    private final RolesService rolesService;

    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Roles>> getAllRoles(){
        return new ResponseEntity<>(rolesService.getAllRoles(), HttpStatus.OK);
    }
}

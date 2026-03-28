package org.example.lmsbackend.service;

import org.example.lmsbackend.model.Roles;
import org.example.lmsbackend.repository.RolesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesService {

    private final RolesRepository rolesRepository;

    public RolesService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public List<Roles> getAllRoles(){
        return rolesRepository.findAll();
    }
}

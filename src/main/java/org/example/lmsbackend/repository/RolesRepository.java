package org.example.lmsbackend.repository;

import org.example.lmsbackend.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Long> {
}

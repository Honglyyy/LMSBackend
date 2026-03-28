package org.example.lmsbackend.repository;

import org.example.lmsbackend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}

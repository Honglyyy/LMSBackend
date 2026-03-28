package org.example.lmsbackend.repository;

import org.example.lmsbackend.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Courses, Long> {
}

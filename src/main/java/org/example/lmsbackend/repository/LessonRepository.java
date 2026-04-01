package org.example.lmsbackend.repository;

import org.example.lmsbackend.model.Lessons;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lessons, Long> {
//    List<Lessons> findSections_SectionId(Long sectionId);
}

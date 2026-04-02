package org.example.lmsbackend.repository;

import org.example.lmsbackend.model.Lessons;
import org.example.lmsbackend.model.Quizzes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quizzes, Long> {
    List<Quizzes> findByLesson_LessonId(Long lessonId);
}

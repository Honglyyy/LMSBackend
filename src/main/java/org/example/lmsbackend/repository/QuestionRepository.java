package org.example.lmsbackend.repository;

import org.example.lmsbackend.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Questions, Long> {
    List<Questions> findByQuiz_QuizId(Long id);
}

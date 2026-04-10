package org.example.lmsbackend.repository;

import org.example.lmsbackend.dto.AnswerDetailDTO;
import org.example.lmsbackend.model.Answers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answers, Long> {
}
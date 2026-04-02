package org.example.lmsbackend.mapper;

import org.example.lmsbackend.dto.LessonResponseDTO;
import org.example.lmsbackend.dto.QuizCreateDTO;
import org.example.lmsbackend.dto.QuizResponseDTO;
import org.example.lmsbackend.model.Lessons;
import org.example.lmsbackend.model.Quizzes;
import org.springframework.stereotype.Component;

@Component
public class QuizMapper {
    public QuizResponseDTO toDto(Quizzes quiz){
        return new QuizResponseDTO(
                quiz.getQuizId(),
                quiz.getQuizTitle(),
                quiz.getTotalPoint(),
                quiz.getLesson().getLessonId(),
                quiz.getLesson().getTitle()
        );
    }

    public Quizzes toEntity(
            QuizCreateDTO dto,
            Lessons lesson
    ){
        Quizzes quiz = new Quizzes();

        quiz.setQuizTitle(dto.title());
        quiz.setTotalPoint(dto.totalPoints());
        quiz.setLesson(lesson);

        return quiz;
    }
}
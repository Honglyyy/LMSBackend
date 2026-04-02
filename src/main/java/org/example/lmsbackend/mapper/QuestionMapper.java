package org.example.lmsbackend.mapper;

import org.example.lmsbackend.dto.QuestionCreateDTO;
import org.example.lmsbackend.dto.QuestionResponseDTO;
import org.example.lmsbackend.model.Questions;
import org.example.lmsbackend.model.Quizzes;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {

    public QuestionResponseDTO toDto(Questions question){
        return new QuestionResponseDTO(
                question.getQuestionId(),
                question.getQuestionText(),
                question.getQuiz().getQuizId()
        );
    }

    public Questions toEntity(
            QuestionCreateDTO dto,
            Quizzes quiz
    ){
        Questions question = new Questions();

        question.setQuestionText(dto.questionText());
        question.setQuiz(quiz);

        return question;
    }
}

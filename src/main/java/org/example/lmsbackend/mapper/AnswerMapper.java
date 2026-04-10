package org.example.lmsbackend.mapper;

import org.example.lmsbackend.dto.AnswerCreateDTO;
import org.example.lmsbackend.dto.AnswerResponseDTO;
import org.example.lmsbackend.dto.LessonCreateDTO;
import org.example.lmsbackend.dto.LessonResponseDTO;
import org.example.lmsbackend.model.Answers;
import org.example.lmsbackend.model.Lessons;
import org.example.lmsbackend.model.Questions;
import org.example.lmsbackend.model.Sections;
import org.springframework.stereotype.Component;

@Component
public class AnswerMapper {
    public AnswerResponseDTO toDto(Answers answer){
        return new AnswerResponseDTO(
                answer.getAnswerId(),
                answer.getAnswerText(),
                answer.getIsCorrect(),
                answer.getQuestion().getQuestionId()
        );
    }

    public Answers toEntity(
            AnswerCreateDTO dto,
            Questions question
    ){
        Answers answers = new Answers();

        answers.setAnswerText(dto.answerText());
        answers.setIsCorrect(dto.isCorrect());
        answers.setQuestion(question);

        return answers;
    }
}

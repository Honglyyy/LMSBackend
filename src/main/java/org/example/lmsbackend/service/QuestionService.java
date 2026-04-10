package org.example.lmsbackend.service;

import org.example.lmsbackend.dto.QuestionCreateDTO;
import org.example.lmsbackend.dto.QuestionResponseDTO;
import org.example.lmsbackend.mapper.QuestionMapper;
import org.example.lmsbackend.model.Questions;
import org.example.lmsbackend.model.Quizzes;
import org.example.lmsbackend.repository.QuestionRepository;
import org.example.lmsbackend.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionMapper questionMapper;
    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    public QuestionService(QuestionMapper questionMapper, QuestionRepository questionRepository, QuizRepository quizRepository) {
        this.questionMapper = questionMapper;
        this.questionRepository = questionRepository;
        this.quizRepository = quizRepository;
    }

    public List<QuestionResponseDTO> getQuestions(){
        return questionRepository.findAll()
                .stream()
                .map(questionMapper::toDto)
                .toList();
    }

    public QuestionResponseDTO addQuesion(QuestionCreateDTO dto){
        Questions question = new Questions();

        Quizzes quiz = quizRepository.findById(dto.quizId()).orElseThrow(()->new RuntimeException("Quiz not found"));


        question = questionMapper.toEntity(dto, quiz);

        return questionMapper.toDto(questionRepository.save(question));
    }

    public QuestionResponseDTO updateQuestion(Long id, QuestionCreateDTO dto){
        Questions existingQuestion = questionRepository.findById(id).orElseThrow(()->new RuntimeException("Questions not found"));

        Quizzes quizId = quizRepository.findById(dto.quizId()).orElseThrow(()-> new RuntimeException("Quiz not found"));

        existingQuestion.setQuestionText(dto.questionText());
        existingQuestion.setQuiz(quizId);

        return questionMapper.toDto(questionRepository.save(existingQuestion));
    }

    public void deleteQuestion(Long id){
        questionRepository.deleteById(id);
    }
}

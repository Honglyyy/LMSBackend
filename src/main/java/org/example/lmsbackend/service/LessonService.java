package org.example.lmsbackend.service;

import org.example.lmsbackend.dto.*;
import org.example.lmsbackend.mapper.LessonMapper;
import org.example.lmsbackend.model.Lessons;
import org.example.lmsbackend.model.Questions;
import org.example.lmsbackend.model.Quizzes;
import org.example.lmsbackend.model.Sections;
import org.example.lmsbackend.repository.LessonRepository;
import org.example.lmsbackend.repository.QuizRepository;
import org.example.lmsbackend.repository.SectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {
    private final LessonMapper lessonMapper;
    private final LessonRepository lessonRepository;
    private final SectionRepository sectionRepository;
    private final QuizRepository quizRepository;

    public LessonService(LessonMapper lessonMapper, LessonRepository lessonRepository, SectionRepository sectionRepository, QuizRepository quizRepository) {
        this.lessonRepository = lessonRepository;
        this.lessonMapper = lessonMapper;
        this.sectionRepository = sectionRepository;
        this.quizRepository = quizRepository;
    }

    public List<LessonResponseDTO> getLessons() {
        return lessonRepository.findAll()
                .stream()
                .map(lessonMapper::toDto)
                .toList();
    }


    public LessonResponseDTO addLesson(LessonCreateDTO dto) {
        Lessons lesson = new Lessons();

        Sections section = sectionRepository.findById(dto.sectionId()).orElse(null);

        lesson = lessonMapper.toEntity(dto, section);

        return lessonMapper.toDto(lessonRepository.save(lesson));
    }

    public LessonResponseDTO updateLesson(Long id, LessonCreateDTO dto) {
        Lessons existingLesson = lessonRepository.findById(id).orElse(null);

        Sections sectionId = sectionRepository.findById(dto.sectionId()).orElse(null);

        existingLesson.setTitle(dto.title());
        existingLesson.setVideoDir(dto.videoDir());
        existingLesson.setSections(sectionId);

        return lessonMapper.toDto(lessonRepository.save(existingLesson));
    }

    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);
    }

    public LessonQuizDTO getLesson(Long lessonId){
        Lessons lessons = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        List<QuizDetailDTO> quizzes = quizRepository.findByLesson_LessonId(lessonId)
                .stream()
                .map(quiz -> new QuizDetailDTO(
                        quiz.getQuizId(),
                        quiz.getQuizTitle(),
                        quiz.getTotalPoint(),
                        quiz.getQuestions().stream()
                                .map(question -> new QuestionDetailDTO(
                                        question.getQuestionId(),
                                        question.getQuestionText(),
                                        question.getPoint(),
                                        question.getAnswers().stream()
                                                .map(answer -> new AnswerDetailDTO(
                                                        answer.getAnswerId(),
                                                        answer.getAnswerText(),
                                                        answer.getIsCorrect()
                                                )).toList()
                                )).toList()
                        )
                ).toList();

        return new LessonQuizDTO(
                lessons.getLessonId(),
                lessons.getTitle(),
                lessons.getVideoDir(),
                quizzes
        );
    }
}

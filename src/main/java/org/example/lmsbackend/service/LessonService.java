package org.example.lmsbackend.service;

import org.example.lmsbackend.dto.LessonCreateDTO;
import org.example.lmsbackend.dto.LessonDetailDTO;
import org.example.lmsbackend.dto.LessonResponseDTO;
import org.example.lmsbackend.dto.QuizDetailDTO;
import org.example.lmsbackend.mapper.LessonMapper;
import org.example.lmsbackend.model.Lessons;
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

    public List<LessonResponseDTO> getLessons(){
        return lessonRepository.findAll()
                .stream()
                .map(lessonMapper::toDto)
                .toList();
    }

    public LessonDetailDTO getLesson(Long id){
        Lessons lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        return toDetailDTO(lesson);
    }


    public LessonResponseDTO addLesson(LessonCreateDTO dto){
        Lessons lesson = new Lessons();

        Sections section = sectionRepository.findById(dto.sectionId()).orElse(null);

        lesson = lessonMapper.toEntity(dto,section);

        return lessonMapper.toDto(lessonRepository.save(lesson));
    }

    public LessonResponseDTO updateLesson(Long id,LessonCreateDTO dto){
        Lessons existingLesson = lessonRepository.findById(id).orElse(null);

        Sections sectionId = sectionRepository.findById(dto.sectionId()).orElse(null);

        existingLesson.setTitle(dto.title());
        existingLesson.setVideoDir(dto.videoDir());
        existingLesson.setSections(sectionId);

        return lessonMapper.toDto(existingLesson);
    }

    public void deleteSection(Long id){
        sectionRepository.deleteById(id);
    }

    public LessonDetailDTO toDetailDTO(Lessons lesson){
        List<QuizDetailDTO> quizzes = quizRepository.findByLesson_LessonId(lesson.getLessonId())
                .stream()
                .map(quiz -> new QuizDetailDTO(
                        quiz.getQuizId(),
                        quiz.getQuizTitle(),
                        quiz.getTotalPoint()
                )).toList();

        return new LessonDetailDTO(
                lesson.getLessonId(),
                lesson.getTitle(),
                lesson.getVideoDir(),
                quizzes
        );
    }
}

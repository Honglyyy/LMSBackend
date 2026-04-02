package org.example.lmsbackend.service;

import org.example.lmsbackend.dto.*;
import org.example.lmsbackend.mapper.LessonMapper;
import org.example.lmsbackend.mapper.SectionMapper;
import org.example.lmsbackend.model.*;
import org.example.lmsbackend.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {

    private final SectionRepository sectionRepository;
    private final SectionMapper sectionMapper;
    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;
    private final QuizRepository quizRepository;
    private final Quizzes quizzes;

    public SectionService(SectionRepository sectionRepository, SectionMapper sectionMapper, CourseRepository courseRepository, CategoryRepository categoryRepository, LessonRepository lessonRepository, QuizRepository quizRepository, Quizzes quizzes) {
        this.courseRepository = courseRepository;
        this.sectionRepository = sectionRepository;
        this.sectionMapper = sectionMapper;
        this.lessonRepository = lessonRepository;
        this.quizRepository = quizRepository;
        this.quizzes = quizzes;
    }

    public List<SectionResponseDTO> getSections(){
        return sectionRepository.findAll()
                .stream()
                .map(sectionMapper::toDto)
                .toList();
    }

    public SectionDetailDTO getSection(Long id){
        Sections section = sectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Section id " + id + " not found"));

        return toSectionDetailDto(section);
    }
    public CourseDetailDTO getSectionByCourseId(Long courseId){
        Courses course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course id " + courseId + " not found"));

        List<SectionDetailDTO> sections = sectionRepository.findByCourse_CourseId(courseId)
                .stream()
                .map(this::toSectionDetailDto)
                .toList();

        List<String> categories = course.getCategories()
                .stream()
                .map(Categories::getCategory)
                .toList();


        return new CourseDetailDTO(
                course.getCourseId(),
                course.getTitle(),
                course.getDescription(),
                course.getPrice(),
                course.getOverallDuration(),
                course.getCoverDir(),
                course.getInstructorId().getUsername(),
                categories,
                sections
        );
    }

    public SectionResponseDTO addSection(SectionCreateDTO dto) {
        Sections section = new Sections();

        Courses courses = courseRepository.findById(dto.courseId())
                .orElseThrow(()->new RuntimeException("Course id "+ dto.courseId() + " is not found!!"));

        section = sectionMapper.toEntity(dto,courses);

        return sectionMapper.toDto(sectionRepository.save(section));
    }

    public SectionResponseDTO updateSection(Long id, SectionCreateDTO dto){
        Sections existingSection = sectionRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("s"));

        Courses courseId = courseRepository.findById(dto.courseId())
                .orElseThrow(()->new RuntimeException("Course id "+ dto.courseId() + " is not found!!"));


        existingSection.setTitle(dto.title());
        existingSection.setDuration(dto.duration());
        existingSection.setCourse(courseId);

        return sectionMapper.toDto(existingSection);
    }

    public void deleteSection(Long id){
        sectionRepository.deleteById(id);
    }

    private SectionDetailDTO toSectionDetailDto(Sections section) {

        List<LessonDetailDTO> lessons = lessonRepository.findBySections_SectionId(section.getSectionId())
                .stream()
                .map(lesson -> new LessonDetailDTO(
                        lesson.getLessonId(),
                        lesson.getTitle(),
                        lesson.getVideoDir(),
                        quizRepository.findByLesson_LessonId(lesson.getLessonId())
                        .stream().map(quiz -> new QuizDetailDTO(
                                quiz.getQuizId(),
                                quiz.getQuizTitle(),
                                quiz.getTotalPoint()
                                )).toList()
                ))
                .toList();

        return new SectionDetailDTO(
                section.getSectionId(),
                section.getTitle(),
                section.getDuration(),
                lessons
        );
    }
}

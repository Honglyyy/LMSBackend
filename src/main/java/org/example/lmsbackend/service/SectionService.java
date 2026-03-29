package org.example.lmsbackend.service;

import org.example.lmsbackend.dto.CourseDetailDTO;
import org.example.lmsbackend.dto.SectionCreateDTO;
import org.example.lmsbackend.dto.SectionDTO;
import org.example.lmsbackend.dto.SectionResponseDTO;
import org.example.lmsbackend.mapper.SectionMapper;
import org.example.lmsbackend.model.Categories;
import org.example.lmsbackend.model.Courses;
import org.example.lmsbackend.model.Sections;
import org.example.lmsbackend.repository.CategoryRepository;
import org.example.lmsbackend.repository.CourseRepository;
import org.example.lmsbackend.repository.SectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {

    private final SectionRepository sectionRepository;
    private final SectionMapper sectionMapper;
    private final CourseRepository courseRepository;

    public SectionService(SectionRepository sectionRepository, SectionMapper sectionMapper, CourseRepository courseRepository, CategoryRepository categoryRepository) {
        this.courseRepository = courseRepository;
        this.sectionRepository = sectionRepository;
        this.sectionMapper = sectionMapper;
    }

    public List<SectionResponseDTO> getSections(){
        return sectionRepository.findAll()
                .stream()
                .map(sectionMapper::toDto)
                .toList();
    }
    public CourseDetailDTO getSectionByCourseId(Long courseId){
        Courses course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course id " + courseId + " not found"));

        List<SectionDTO> sections = sectionRepository.findByCourse_CourseId(courseId)
                .stream()
                .map(section -> new SectionDTO(
                        section.getSectionId(),
                        section.getTitle(),
                        section.getDuration()
                ))
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
}

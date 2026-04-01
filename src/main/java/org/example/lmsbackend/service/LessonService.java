package org.example.lmsbackend.service;

import org.example.lmsbackend.dto.LessonCreateDTO;
import org.example.lmsbackend.dto.LessonResponseDTO;
import org.example.lmsbackend.mapper.LessonMapper;
import org.example.lmsbackend.model.Lessons;
import org.example.lmsbackend.model.Sections;
import org.example.lmsbackend.repository.LessonRepository;
import org.example.lmsbackend.repository.SectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {
    private final LessonMapper lessonMapper;
    private final LessonRepository lessonRepository;
    private final SectionRepository sectionRepository;

    public LessonService(LessonMapper lessonMapper, LessonRepository lessonRepository, SectionRepository sectionRepository) {
        this.lessonRepository = lessonRepository;
        this.lessonMapper = lessonMapper;
        this.sectionRepository = sectionRepository;
    }

    public List<LessonResponseDTO> getLessons(){
        return lessonRepository.findAll()
                .stream()
                .map(lessonMapper::toDto)
                .toList();
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
}

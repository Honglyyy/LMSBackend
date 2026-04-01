package org.example.lmsbackend.mapper;

import org.example.lmsbackend.dto.LessonCreateDTO;
import org.example.lmsbackend.dto.LessonResponseDTO;
import org.example.lmsbackend.model.Lessons;
import org.example.lmsbackend.model.Sections;
import org.springframework.stereotype.Component;

@Component
public class LessonMapper {

    public LessonResponseDTO toDto(Lessons lesson){
        return new LessonResponseDTO(
                lesson.getLessonId(),
                lesson.getTitle(),
                lesson.getVideoDir(),
                lesson.getSections().getTitle()
        );
    }

    public Lessons toEntity(
            LessonCreateDTO dto,
            Sections section
    ){
        Lessons lesson = new Lessons();

        lesson.setTitle(dto.title());
        lesson.setVideoDir(dto.videoDir());
        lesson.setSections(section);

        return lesson;
    }

}

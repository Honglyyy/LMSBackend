package org.example.lmsbackend.mapper;

import org.example.lmsbackend.dto.SectionCreateDTO;
import org.example.lmsbackend.dto.SectionResponseDTO;
import org.example.lmsbackend.model.Categories;
import org.example.lmsbackend.model.Courses;
import org.example.lmsbackend.model.Sections;
import org.springframework.stereotype.Component;

@Component
public class SectionMapper {
    public SectionResponseDTO toDto(
            Sections section
    ){
        return new SectionResponseDTO(
                section.getSectionId(),
                section.getTitle(),
                section.getDuration(),
                section.getCourse().getCourseId(),
                section.getCourse().getTitle()
        );
    }

    public Sections toEntity(
            SectionCreateDTO dto,
            Courses course
    ){
        Sections section = new Sections();
        section.setTitle(dto.title());
        section.setDuration(dto.duration());
        section.setCourse(course);

        return section;
    }
}

package org.example.lmsbackend.mapper;

import org.example.lmsbackend.dto.CourseCreateDTO;
import org.example.lmsbackend.dto.CourseResponseDTO;
import org.example.lmsbackend.model.Categories;
import org.example.lmsbackend.model.Courses;
import org.example.lmsbackend.model.Users;
import org.springframework.stereotype.Component;

import java.util.List;
//TODO
//Forgot fields of duration and dir
@Component
public class CourseMapper {
    public Courses toEntity(
            CourseCreateDTO dto,
            Users instructor,
            List<Categories> categories
    ){
        Courses course = new Courses();

        course.setTitle(dto.title());
        course.setDescription(dto.description());
        course.setPrice(dto.price());
        course.setOverallDuration(dto.overallDuration());
        course.setCoverDir(dto.coverDir());
        course.setInstructorId(instructor);
        course.setCategories(categories);

        return course;
    }

    public CourseResponseDTO toDTO(Courses course){
        List<String> coursesName = course.getCategories()
                .stream()
                .map(Categories::getCategory)
                .toList();

        return new CourseResponseDTO(
                course.getCourseId(),
                course.getTitle(),
                course.getDescription(),
                course.getPrice(),
                course.getOverallDuration(),
                course.getCoverDir(),
                course.getInstructorId().getUsername(),
                coursesName
        );
    }
}

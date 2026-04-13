package org.example.lmsbackend.mapper;

import org.example.lmsbackend.dto.CourseCreateDTO;
import org.example.lmsbackend.dto.CourseDetailDTO;
import org.example.lmsbackend.dto.CourseResponseDTO;
import org.example.lmsbackend.model.Categories;
import org.example.lmsbackend.model.Courses;
import org.example.lmsbackend.model.Users;
import org.example.lmsbackend.repository.CourseReviewRepository;
import org.example.lmsbackend.repository.EnrollmentRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.OptionalDouble;

//TODO
//Forgot fields of duration and dir
@Component
public class CourseMapper {

    private final CourseReviewRepository courseReviewRepository;
    private final EnrollmentRepository enrollmentRepository;

    public CourseMapper(CourseReviewRepository courseReviewRepository, EnrollmentRepository enrollmentRepository) {
        this.courseReviewRepository = courseReviewRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

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

    public CourseResponseDTO toDTO(
            Courses course
    ){
        List<String> coursesName = course.getCategories()
                .stream()
                .map(Categories::getCategory)
                .toList();

        Double rating = courseReviewRepository.findByCourse_CourseId(course.getCourseId())
                .stream()
                .mapToDouble(rate -> rate.getRating())
                .average().orElse(0.0);

        Long enrollment = enrollmentRepository.findByCourse_CourseId(course.getCourseId())
                .stream()
                .count();

        return new CourseResponseDTO(
                course.getCourseId(),
                course.getTitle(),
                course.getDescription(),
                course.getPrice(),
                course.getOverallDuration(),
                course.getCoverDir(),
                course.getInstructorId().getUsername(),
                coursesName,
                rating,
                enrollment
        );
    }
}

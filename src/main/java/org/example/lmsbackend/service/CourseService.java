package org.example.lmsbackend.service;

import org.example.lmsbackend.dto.CourseCreateDTO;
import org.example.lmsbackend.dto.CourseResponseDTO;
import org.example.lmsbackend.mapper.CourseMapper;
import org.example.lmsbackend.model.Categories;
import org.example.lmsbackend.model.Courses;
import org.example.lmsbackend.model.Users;
import org.example.lmsbackend.repository.CategoryRepository;
import org.example.lmsbackend.repository.CourseRepository;
import org.example.lmsbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.categoryRepository = categoryRepository;
        this.courseMapper = courseMapper;
        this.userRepository = userRepository;
    }

    public List<CourseResponseDTO> getAllCourses(){
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toDTO)
                .toList();
    }

    public CourseResponseDTO getCourse(Long id){
        return courseMapper.toDTO(courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course id " + id +" is not found!!")));
    }

    public CourseResponseDTO addCourse(CourseCreateDTO dto){
        Users instructor = userRepository.findById(dto.instructorId())
                .orElseThrow(() -> new RuntimeException("Instructor id " + dto.instructorId() + " is now found!!"));

        List<Categories> categories = categoryRepository.findAllById(dto.categoryId());

        Courses course = courseMapper.toEntity(dto,instructor,categories);

        return courseMapper.toDTO(courseRepository.save(course));
    }

    public void deleteCourse(Long id){
        courseRepository.deleteById(id);
    }

    public CourseResponseDTO updateCourse(Long id, CourseCreateDTO dto){
        Courses existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course id" + id + " not found"));

        Users instructor = userRepository.findById(dto.instructorId())
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        List<Categories> categories = categoryRepository.findAllById(dto.categoryId());

        existingCourse.setTitle(dto.title());
        existingCourse.setDescription(dto.description());
        existingCourse.setPrice(dto.price());
        existingCourse.setOverallDuration(dto.overallDuration());
        existingCourse.setCoverDir(dto.coverDir());
        existingCourse.setInstructorId(instructor);
        existingCourse.setCategories(categories);

        return courseMapper.toDTO(courseRepository.save(existingCourse));
    }
}

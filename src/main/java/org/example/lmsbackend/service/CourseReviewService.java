package org.example.lmsbackend.service;

import org.example.lmsbackend.dto.CourseResponseDTO;
import org.example.lmsbackend.dto.CourseReviewCreateDTO;
import org.example.lmsbackend.dto.CourseReviewResponseDTO;
import org.example.lmsbackend.mapper.CourseMapper;
import org.example.lmsbackend.mapper.CourseReviewMapper;
import org.example.lmsbackend.model.CourseReviews;
import org.example.lmsbackend.model.Courses;
import org.example.lmsbackend.model.Users;
import org.example.lmsbackend.repository.CourseRepository;
import org.example.lmsbackend.repository.CourseReviewRepository;
import org.example.lmsbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseReviewService {


    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final CourseReviewMapper courseReviewMapper;
    private final CourseReviewRepository courseReviewRepository;

    public CourseReviewService(UserRepository userRepository, CourseRepository courseRepository, CourseReviewMapper courseReviewMapper, CourseReviewRepository courseReviewRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.courseReviewMapper = courseReviewMapper;
        this.courseReviewRepository = courseReviewRepository;
    }

    public CourseReviewResponseDTO addReview(CourseReviewCreateDTO dto){
        Users userId = userRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("User id " + dto.userId() + " is not found!!"));

        Courses courseId = courseRepository.findById(dto.courseId())
                .orElseThrow(() -> new RuntimeException("Course id " + dto.courseId() + " is not found!!"));

        CourseReviews review = courseReviewMapper.toEntity(dto, userId, courseId);

        return courseReviewMapper.toDto(courseReviewRepository.save(review));
    }

}

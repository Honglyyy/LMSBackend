package org.example.lmsbackend.service;

import org.apache.catalina.User;
import org.example.lmsbackend.dto.EnrollmentCreateDTO;
import org.example.lmsbackend.dto.EnrollmentResponseDTO;
import org.example.lmsbackend.model.Courses;
import org.example.lmsbackend.model.Enrollments;
import org.example.lmsbackend.model.Users;
import org.example.lmsbackend.repository.CourseRepository;
import org.example.lmsbackend.repository.EnrollmentRepository;
import org.example.lmsbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, UserRepository userRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public List<EnrollmentResponseDTO> getEnrollments(){
        return enrollmentRepository.findAll()
                .stream()
                .map(enrollment -> new EnrollmentResponseDTO(
                        enrollment.getEnrollmentId(),
                        enrollment.getUser().getUserId(),
                        List.of(enrollment.getCourse().getCourseId()),
                        enrollment.getIsPaid(),
                        enrollment.getEnrolledAt()
                ))
                .toList();
    }

    public EnrollmentResponseDTO addEnrollment(EnrollmentCreateDTO dto){
        Enrollments enrollments = new Enrollments();
        Users user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new RuntimeException("User id " + dto.userId() + " is now found!!"));

        Courses course = courseRepository.findById(dto.courseId())
                .orElseThrow(() -> new RuntimeException("Course id " + dto.courseId() + " is now found!!"));

        enrollments.setCourse(course);
        enrollments.setUser(user);
        enrollments.setIsPaid(dto.isPaid());

        enrollmentRepository.save(enrollments);

        return new EnrollmentResponseDTO(
                enrollments.getEnrollmentId(),
                enrollments.getUser().getUserId(),
                List.of(enrollments.getCourse().getCourseId()),
                enrollments.getIsPaid(),
                enrollments.getEnrolledAt()
        );
    }

}

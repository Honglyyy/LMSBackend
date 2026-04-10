package org.example.lmsbackend.repository;

import org.example.lmsbackend.model.CourseReviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseReviewRepository extends JpaRepository<CourseReviews, Long> {
    List<CourseReviews> findByCourse_CourseId(Long courseId);
}

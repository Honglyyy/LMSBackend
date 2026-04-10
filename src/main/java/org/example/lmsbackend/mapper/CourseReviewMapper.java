package org.example.lmsbackend.mapper;

import org.example.lmsbackend.dto.CourseReviewCreateDTO;
import org.example.lmsbackend.dto.CourseReviewResponseDTO;
import org.example.lmsbackend.model.CourseReviews;
import org.example.lmsbackend.model.Courses;
import org.example.lmsbackend.model.Users;
import org.springframework.stereotype.Component;

@Component
public class CourseReviewMapper {
    public CourseReviewResponseDTO toDto(CourseReviews review){

        Users user = new Users();
        Courses course = new Courses();

        return new CourseReviewResponseDTO(
                review.getReviewId(),
                review.getReviewText(),
                review.getRating(),
                review.getUser().getUsername(),
                review.getCourse().getTitle()
        );
    }

    public CourseReviews toEntity(CourseReviewCreateDTO dto, Users user, Courses course){
        CourseReviews review = new CourseReviews();

        review.setReviewText(dto.reviewText());
        review.setRating(dto.rating());
        review.setUser(user);
        review.setCourse(course);

        return review;
    }
}

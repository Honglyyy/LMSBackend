package org.example.lmsbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Enrollments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_id")
    private Long enrollmentId;

    @Column(name = "enrolled_at")
    private LocalDateTime enrolledAt;

    private Boolean status;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @ManyToOne
    private Users user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Courses course;

    @OneToMany(mappedBy = "enrollment")
    private List<QuizAttempts> quizAttempts;

    @OneToMany(mappedBy = "enrollment")
    private List<LessonProgress> lessonProgress;

    @OneToMany(mappedBy = "enrollment")
    private List<Certificates> certificates;
}

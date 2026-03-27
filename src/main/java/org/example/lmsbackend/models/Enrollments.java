package org.example.lmsbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
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
    private long enrollmentId;

    @Column(name = "enrolled_at")
    private LocalDateTime enrolledAt;

    private boolean status;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @Column(name = "is_paid")
    private boolean isPaid;

    @ManyToOne
    @JsonIgnore
    private Users user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "course_id")
    private Courses course;

    @OneToMany(mappedBy = "enrollment")
    @JsonIgnore
    private List<QuizAttempts> quizAttempts;

    @OneToMany(mappedBy = "enrollment")
    @JsonIgnore
    private List<LessonProgress> lessonProgress;

    @OneToMany(mappedBy = "enrollment")
    @JsonIgnore
    private List<Certificates> certificates;
}

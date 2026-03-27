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

public class QuizAttempts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attempt_id")
    private long attemptId;

    private double score;

    @Column(name = "attempted_at")
    @CreationTimestamp
    private LocalDateTime attemptedAt;

    @ManyToOne
    @JoinColumn(name = "enrollment_id")
    @JsonIgnore
    private Enrollments enrollment;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    @JsonIgnore
    private Quizzes quiz;
}

package org.example.lmsbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class LessonProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "progress_id")
    private long progressId;

    @Column(name = "is_completed")
    private boolean isCompleted;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @ManyToOne
    @JoinColumn(name = "enrollment_id")
    private Enrollments enrollment;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lessons lesson;
}

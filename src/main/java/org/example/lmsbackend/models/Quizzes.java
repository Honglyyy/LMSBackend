package org.example.lmsbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

public class Quizzes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private long quizId;

    @Column(name = "quiz_title")
    private String quizTitle;

    @Column(name = "total_point")
    private double totalPoint;

    @OneToOne
    @JoinColumn(name = "lesson_id")
    private Lessons lesson;

    @OneToMany(mappedBy = "quiz")
    @JsonManagedReference
    private List<Questions> questions;

    @OneToMany(mappedBy = "quiz")
    @JsonIgnore
    private List<QuizAttempts> quizAttempts;
}

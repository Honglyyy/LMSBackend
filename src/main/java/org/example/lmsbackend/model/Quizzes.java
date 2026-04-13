package org.example.lmsbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

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
    private Long quizId;

    @Column(name = "quiz_title")
    private String quizTitle;

    @Column(name = "total_point")
    private Double totalPoint;

    @OneToOne
    @JoinColumn(name = "lesson_id")
    private Lessons lesson;

    @OneToMany(mappedBy = "quiz")
    private List<Questions> questions;

//    @OneToMany(mappedBy = "quiz")
//    private List<QuizAttempts> quizAttempts;
}

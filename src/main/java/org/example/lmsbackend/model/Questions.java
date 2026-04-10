package org.example.lmsbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "question_text")
    private String questionText;

    private Long point;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quizzes quiz;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answers> answers;
}

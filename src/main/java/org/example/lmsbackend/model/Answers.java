package org.example.lmsbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Answers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private long answerId;

    @Column(name = "answer_text")
    private String answerText;

    private boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Questions question;
}

package org.example.lmsbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

public class Lessons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    private Long lessonId;

    @Column(name = "lesson_title")
    private String title;

    @Column(name = "video_dir")
    private String videoDir;

    @CreationTimestamp
    private LocalDateTime createAt;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Sections sections;

//    @OneToMany(mappedBy = "lesson")
//    private List<LessonProgress> lessonProgress;

    @OneToOne(mappedBy = "lesson")
    private Quizzes quiz;
}

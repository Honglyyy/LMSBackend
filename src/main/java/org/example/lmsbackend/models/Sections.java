package org.example.lmsbackend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Sections {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id")
    private long sectionId;

    @Column(name = "section_title")
    private String title;

    @Column(name = "section_duration")
    private String duration;

    @CreationTimestamp
    private LocalDateTime createAt;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonBackReference
    private Courses course;

    @OneToMany(mappedBy = "sections", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Lessons> lessons;
}


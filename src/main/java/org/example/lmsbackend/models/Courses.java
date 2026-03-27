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
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private long courseId;

    private String title;

    private String description;

    @Column(name = "overall_duration")
    private String overallDuration;

    @Column(name = "cover_dir")
    private String coverDir;

    private double price;

    @CreationTimestamp
    private LocalDateTime createAt;

    @ManyToMany
    @JoinTable(
            name = "course_category",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Categories> categories;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Sections> sections;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private List<CourseReviews> reviews;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private List<Enrollments> enrollments;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    @JsonIgnore
    private Users instructorId;
}

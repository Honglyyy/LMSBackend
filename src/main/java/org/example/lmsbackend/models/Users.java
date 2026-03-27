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

public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;

    private String username ;

    private String password;

    @CreationTimestamp
    private LocalDateTime createAt;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Roles role;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<CourseReviews> review;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Enrollments> enrollments;

}

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

public class Certificates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certificate_id")
    private long certificateId;

    @Column(name = "certificate_dir")
    private String certificateDir;

    @CreationTimestamp
    @Column(name = "issued_at")
    private LocalDateTime issuedAt;

    @ManyToOne
    @JoinColumn(name = "enrollment_id")
    @JsonIgnore
    private Enrollments enrollment;
}

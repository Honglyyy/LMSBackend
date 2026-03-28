package org.example.lmsbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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
    private Long certificateId;

    @Column(name = "certificate_dir")
    private String certificateDir;

    @CreationTimestamp
    @Column(name = "issued_at")
    private LocalDateTime issuedAt;

    @ManyToOne
    @JoinColumn(name = "enrollment_id")
    private Enrollments enrollment;
}

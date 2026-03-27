package org.example.lmsbackend.models;

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
public class Roles {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long role_id;

    private String role_name;
}

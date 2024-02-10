package br.com.norrels.courseapi.modules.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "course")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private String category;

    private boolean active;

    @CreationTimestamp
    private LocalDateTime create_at;

    @UpdateTimestamp
    private LocalDateTime update_at;
}

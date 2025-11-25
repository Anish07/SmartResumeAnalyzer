package com.smartresume.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "resumes")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @Lob
    @Column(nullable = false)
    private String content; // The extracted text

    @Lob
    @Column(nullable = false)
    private String analysisResult; // The AI analysis JSON

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDateTime createdAt = LocalDateTime.now();
}

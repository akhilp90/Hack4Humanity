package com.example.Includify_Backend.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name="saved_jobs", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","job_post_id"}))
public class SavedJob {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="job_post_id")
    private JobPost jobPost;

    private Instant savedAt = Instant.now();

    public SavedJob(){}
    // getters/setters...
}
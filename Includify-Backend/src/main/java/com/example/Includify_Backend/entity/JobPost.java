package com.example.Includify_Backend.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "job_posts")
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable=false)
    private String title;

    @Column(length=2000)
    private String description;

    private String location;
    private double salaryMin;
    private double salaryMax;

    @ManyToOne(fetch = FetchType.LAZY)
    private User poster; // job poster

    private Instant createdAt = Instant.now();

    private boolean active = true;

    // constructors, getters, setters
    public JobPost(){}
    // getters/setters...
}
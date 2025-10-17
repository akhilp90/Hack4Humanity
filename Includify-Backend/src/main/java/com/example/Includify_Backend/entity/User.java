package com.example.Includify_Backend.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false)
    private String password; // hashed

    @Enumerated(EnumType.STRING)
    private Role role;

    private Instant createdAt = Instant.now();

    public enum Role { JOB_SEEKER, JOB_POSTER, ADMIN }

    // constructors, getters, setters
    public User(){}

    public User(String name, String email, String password, Role role){
        this.name=name; this.email=email; this.password=password; this.role=role;
    }

    // getters & setters...
    // (omitted for brevity; include standard getters/setters)
    // IDE can generate them
}
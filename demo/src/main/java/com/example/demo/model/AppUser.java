package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 32)
    private UserRole role;

    @Column(name = "full_name", nullable = false, length = 255)
    private String fullName;

    @Column(name = "email", unique = true, length = 255)
    private String email;

    @Column(name = "password_hash", columnDefinition = "text")
    private String passwordHash;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    protected AppUser() {}

    public AppUser(UserRole role, String fullName, String email) {
        this.role = role;
        this.fullName = fullName;
        this.email = email;
    }

    public Long getUserId() { return userId; }
    public UserRole getRole() { return role; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
}

package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "external_id", unique = true, length = 64)
    private String externalId;

    @Column(name = "full_name", nullable = false, length = 255)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false, length = 16)
    private Sex sex = Sex.UNKNOWN;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "height_cm", precision = 5, scale = 2)
    private BigDecimal heightCm;

    @Column(name = "weight_kg", precision = 5, scale = 2)
    private BigDecimal weightKg;

    @Column(name = "notes", columnDefinition = "text")
    private String notes;

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    protected Patient() {}

    public Patient(String fullName, Sex sex) {
        this.fullName = fullName;
        this.sex = sex;
    }

    public Long getPatientId() { return patientId; }
    public String getFullName() { return fullName; }
    public Sex getSex() { return sex; }
}

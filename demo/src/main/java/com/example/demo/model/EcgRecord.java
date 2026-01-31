package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ecg_record")
public class EcgRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ecg_id")
    private Long ecgId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "recorded_at", nullable = false)
    private LocalDateTime recordedAt;

    @Column(name = "lead_count", nullable = false)
    private int leadCount;

    @Column(name = "sampling_hz", nullable = false)
    private int samplingHz;

    @Column(name = "duration_sec", precision = 8, scale = 3)
    private BigDecimal durationSec;

    @Column(name = "device_name", length = 255)
    private String deviceName;

    @Column(name = "data_format", nullable = false, length = 32)
    private String dataFormat = "RAW";

    @Column(name = "storage_uri", columnDefinition = "text")
    private String storageUri;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_user")
    private AppUser createdByUser;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    protected EcgRecord() {}

    public EcgRecord(Patient patient, LocalDateTime recordedAt, int leadCount, int samplingHz) {
        this.patient = patient;
        this.recordedAt = recordedAt;
        this.leadCount = leadCount;
        this.samplingHz = samplingHz;
    }

    public Long getEcgId() { return ecgId; }
    public Patient getPatient() { return patient; }
}

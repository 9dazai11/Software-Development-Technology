package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "analysis_result")
public class AnalysisResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Long resultId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ecg_id", nullable = false, unique = true)
    private EcgRecord ecgRecord;

    @Enumerated(EnumType.STRING)
    @Column(name = "risk_level", nullable = false, length = 16)
    private RiskLevel riskLevel = RiskLevel.LOW;

    @Column(name = "model_name", length = 128)
    private String modelName;

    @Column(name = "model_version", length = 64)
    private String modelVersion;

    @Column(name = "confidence", precision = 4, scale = 3)
    private BigDecimal confidence;

    @Column(name = "summary", columnDefinition = "text")
    private String summary;

    @Column(name = "recommendations", columnDefinition = "text")
    private String recommendations;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    protected AnalysisResult() {}

    public AnalysisResult(EcgRecord ecgRecord, RiskLevel riskLevel) {
        this.ecgRecord = ecgRecord;
        this.riskLevel = riskLevel;
    }
}

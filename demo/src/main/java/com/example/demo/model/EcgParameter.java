package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ecg_parameter")
public class EcgParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "param_id")
    private Long paramId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ecg_id", nullable = false, unique = true)
    private EcgRecord ecgRecord;

    @Column(name = "hr_bpm", precision = 6, scale = 2)
    private BigDecimal hrBpm;

    @Column(name = "qrs_ms", precision = 7, scale = 2)
    private BigDecimal qrsMs;

    @Column(name = "computed_at", nullable = false)
    private LocalDateTime computedAt;
    
    @PrePersist
    void prePersist() {
        if (computedAt == null) {
        	computedAt = LocalDateTime.now();
        }
    }

    protected EcgParameter() {}

    public EcgParameter(EcgRecord ecgRecord, BigDecimal hrBpm, BigDecimal qrsMs) {
        this.ecgRecord = ecgRecord;
        this.hrBpm = hrBpm;
        this.qrsMs = qrsMs;
    }
}

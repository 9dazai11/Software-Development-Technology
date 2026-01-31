package com.example.demo.repo;

import com.example.demo.model.EcgRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EcgRecordRepository extends JpaRepository<EcgRecord, Long> {
}

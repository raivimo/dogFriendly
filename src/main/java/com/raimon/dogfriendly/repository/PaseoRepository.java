package com.raimon.dogfriendly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raimon.dogfriendly.entity.PaseoEntity;

public interface PaseoRepository extends JpaRepository<PaseoEntity, Long> {
    
}

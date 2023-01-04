package com.raimon.dogfriendly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raimon.dogfriendly.entity.FacturaEntity;

public interface FacturaRepository extends JpaRepository<FacturaEntity, Long> {
    
}

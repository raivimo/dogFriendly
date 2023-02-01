package com.raimon.dogfriendly.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.raimon.dogfriendly.entity.PaseoEntity;

public interface PaseoRepository extends JpaRepository<PaseoEntity, Long> {

    Page<PaseoEntity>findByLugar(String lugar, Pageable oPageable);

    
}

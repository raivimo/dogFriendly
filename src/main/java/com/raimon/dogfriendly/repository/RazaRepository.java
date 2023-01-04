package com.raimon.dogfriendly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raimon.dogfriendly.entity.RazaEntity;

public interface RazaRepository extends JpaRepository<RazaEntity, Long> {
    
}

package com.raimon.dogfriendly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raimon.dogfriendly.entity.PerroEntity;

public interface PerroRepository extends JpaRepository<PerroEntity, Long> {
    
}

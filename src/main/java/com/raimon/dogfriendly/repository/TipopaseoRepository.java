package com.raimon.dogfriendly.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.raimon.dogfriendly.entity.TipopaseoEntity;

public interface TipopaseoRepository extends JpaRepository<TipopaseoEntity, Long> {

    Page<TipopaseoEntity>findByNombre(String nombre, Pageable oPageable);
    
    
}

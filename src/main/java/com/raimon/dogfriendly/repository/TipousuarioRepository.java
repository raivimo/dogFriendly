package com.raimon.dogfriendly.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.raimon.dogfriendly.entity.TipousuarioEntity;

public interface TipousuarioRepository extends JpaRepository<TipousuarioEntity, Long> {

    Page<TipousuarioEntity> findByNombre(String nombre, Pageable oPageable);
}

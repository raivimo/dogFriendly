package com.raimon.dogfriendly.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.raimon.dogfriendly.entity.FacturaEntity;

public interface FacturaRepository extends JpaRepository<FacturaEntity, Long> {

    Page<FacturaEntity> findByFechaIgnoreCaseContainingOrIvaIgnoreCaseContainingOrPagadoIgnoreCaseContaining(
            String fecha, String iva, String pagado, Pageable oPageable);

    Page<FacturaEntity> findByPaseoId(Long lPaseo, Pageable oPageable);

    Page<FacturaEntity> findByPaseoIdAndFechaIgnoreCaseContainingOrIvaIgnoreCaseContainingOrPagadoIgnoreCaseContaining(
            Long lPaseo, String fecha, String iva, String pagado, Pageable oPageable);

}

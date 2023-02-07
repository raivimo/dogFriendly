package com.raimon.dogfriendly.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.raimon.dogfriendly.entity.PerroEntity;

public interface PerroRepository extends JpaRepository<PerroEntity, Long> {

        Page<PerroEntity> findByUsuarioId(Long usuario, Pageable oPageable);

        Page<PerroEntity> findByRazaId(Long raza, Pageable oPageable);

        Page<PerroEntity> findByNombreIgnoreCaseContaining(String strFilter, Pageable oPageable);

        @Query(value = "SELECT * FROM perro WHERE id_usuario = ?1 AND (nombre LIKE  %?2% AND fechaNacimiento LIKE %?3%)", nativeQuery = true)
        Page<PerroEntity> findByUsuarioIdAndNombreIgnoreCaseContainingOrFechaNacimientoIgnoreCaseContaining(
                        Long id_usuario,
                        String nombre, String fechaNacimiento, Pageable oPageable);

        @Query(value = "SELECT * FROM perro WHERE id_raza = ?1 AND (nombre LIKE  %?2% AND fechaNacimiento LIKE %?3%)", nativeQuery = true)
        Page<PerroEntity> findByRazaIdAndNombreIgnoreCaseContainingOrFechaNacimientoIgnoreCaseContaining(Long id_raza,
                        String nombre, String fechaNacimiento, Pageable oPageable);

        @Query(value = "SELECT * FROM perro WHERE id_raza = ?1 AND id_usuario = ?2 AND (nombre LIKE  %?3% AND fechaNacimiento LIKE %?4%)", nativeQuery = true)
        Page<PerroEntity> findByRazaIdAndUsuarioIdAndNombreIgnoreCaseContainingOrFechaNacimiento(Long id_raza,
                        Long id_usuario, String nombre, String fechaNacimiento, Pageable oPageable);

        Page<PerroEntity> findByRazaIdAndUsuarioId(Long id_raza, Long id_usuario, Pageable oPageable);

}

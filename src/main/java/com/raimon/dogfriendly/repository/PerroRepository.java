package com.raimon.dogfriendly.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.raimon.dogfriendly.entity.PerroEntity;

public interface PerroRepository extends JpaRepository<PerroEntity, Long> {

    Page<PerroEntity> findByUsuarioId(Long usuario, Pageable oPageable);
    Page<PerroEntity> findByRazaId(Long raza, Pageable oPageable); 


    Page<PerroEntity> findByNombreIgnoreCase(String strFilter, Pageable oPageable);
    
    @Query(value = "SELECT * FROM perro WHERE id_usuario=?1 AND id_raza IN (SELECT id FROM usuario WHERE id_usuario = ?2)", nativeQuery = true)
    Page<PerroEntity> findByUsuarioIdAndRazaId(long id_usuario, long id_raza, Pageable oPageable);

    @Query(value = "SELECT * FROM perro WHERE id_usuario =?1 AND(nombre LIKE  %?2% OR fecha LIKE %?3% OR genero LIKE %?4% OR peso LIKE %?5% OR sociable LIKE %?6% OR puedeIrSuelto LIKE %?7% OR esJugueton LIKE %?8%)" , nativeQuery = true)
    Page<PerroEntity>findByUsuarioIdAndNombreContainingOrFechaContainingOrGeneroContainingOrPesoContainingOrSociableContainingOrPuedeIrSueltoContainingOrEsJuguetonContaining(Long lUsuario, String nombre, String fecha, String genero, String peso, String sociable, String puedeIrSuelto, String jugueton, Pageable oPageable);

    @Query(value = "SELECT * FROM perro WHERE id_raza =?1 AND (nombre LIKE  %?2% OR fecha LIKE %?3% OR genero LIKE %?4% OR peso LIKE %?5% OR sociable LIKE %?6% OR puedeIrSuelto LIKE %?7% OR esJugueton LIKE %?8%)" , nativeQuery = true)
    Page<PerroEntity>findByRazaIdAndNombreContainingOrFechaContainingOrGeneroContainingOrPesoContainingOrSociableContainingOrPuedeIrSueltoContainingOrEsJuguetonContaining(Long lRaza, String nombre, String fecha, String genero, String peso, String sociable, String puedeIrSuelto, String jugueton, Pageable oPageable);


    
}

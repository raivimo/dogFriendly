package com.raimon.dogfriendly.repository;

import java.util.List;

import javax.persistence.NamedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.raimon.dogfriendly.entity.PaseoEntity;

public interface PaseoRepository extends JpaRepository<PaseoEntity, Long> {

        Page<PaseoEntity> findByLugar(String lugar, Pageable oPageable);

        Page<PaseoEntity>findByUsuarioId(Long lUsuario, Pageable oPageable);

        @Query(value = "SELECT * FROM usuario INNER JOIN perro ON usuario.id = perro.id_usuario INNER JOIN paseo ON perro.id = paseo.id_perro WHERE usuario.id = ?1",  nativeQuery = true)
        Page<PaseoEntity>paseosDueñoMascostas(Long lUsuario, Pageable oPageable);

        @Query(value = "SELECT * FROM paseo WHERE (fecha LIKE  %?1% OR lugar LIKE %?2%)", nativeQuery = true)
        Page<PaseoEntity>findByFechaOrLugarContaining(String fecha, String lugar, Pageable oPageable);
        

        @Query(value = "SELECT * FROM paseo WHERE id_usuario = ?1 AND id_perro = ?2 AND (fecha LIKE  %?3% OR lugar LIKE %?4%)", nativeQuery = true)
        Page<PaseoEntity>findByUsuarioIdAndPerroIdAndFechaOrLugarContaining(Long lUsuario, Long lPerro, String fecha, String lugar, Pageable oPageable);
        
        @Query(value = "SELECT * FROM paseo WHERE id_usuario = ?1  AND (fecha LIKE  %?2% OR lugar LIKE %?3%)", nativeQuery = true)
        Page<PaseoEntity>findByUsuarioIdAndFechaOrLugarContaining(Long lUsuario, String fecha, String Lugar, Pageable oPageable);
        
        Page<PaseoEntity> findByPerroId(Long lPerro, Pageable oPageable);

        Page<PaseoEntity> findByTipopaseoId(Long lTipopaseo, Pageable oPageable);

        @Query(value = "SELECT * FROM paseo WHERE id_tipopaseo = ?1  AND (fecha LIKE  %?2% OR lugar LIKE %?3%)", nativeQuery = true)
        Page<PaseoEntity>findByTipopaseoIdAndFechaContainingOrLugarContaining(Long lTipopaseo, String fecha, String Lugar, Pageable oPageable);
        
        Page<PaseoEntity>findByTipopaseoIdAndUsuarioId(Long lTipopaseo, Long lUsuario, Pageable oPageable);

        @Query(value = "SELECT * FROM paseo WHERE id_tipopaseo  = ?1  AND id_usuario = ?2 AND (fecha LIKE  %?3% OR lugar LIKE %?4%)", nativeQuery = true)
        Page<PaseoEntity>findByTipopaseoIdAndUsuarioIdAndFechaOrLugarContaining(Long lTipopaseo,Long lUsuario, String fecha, String lugar, Pageable oPageable);


        Page<PaseoEntity> findByUsuarioIdAndPerroId(Long lUsuario, Long lPerro, Pageable oPageable);

        Page<PaseoEntity> findByTipopaseoIdAndUsuarioIdAndPerroId(Long lTipopaseo, Long lUsuario, Long lPerro,
                        Pageable oPageable);

        @Query(value = "SELECT * FROM paseo WHERE id_perro = ?1  AND (fecha LIKE  %?2% OR lugar LIKE %?3%)", nativeQuery = true)
        Page<PaseoEntity> findByPerroIdAndFechaContainingOrLugarContaining(Long id_perro, String fecha, String lugar,
                        Pageable oPageable);
                        
        @Query(value = "SELECT * FROM paseo WHERE id_usuario = ?1  AND id_perro = ?2 AND (fecha LIKE  %?3% OR lugar LIKE %?4%)", nativeQuery = true)
        Page<PaseoEntity> findByUsuarioIdAndPerroIdAndFechaContainingOrLugarContaining(Long lUsuario, Long lPerro,
                        String fecha,
                        String lugar, Pageable oPageable);
                        
        @Query(value = "SELECT * FROM paseo WHERE id_tipopaseo = ?1  AND id_usuario = ?2 AND id_perro =?3 AND (fecha LIKE  %?4% OR lugar LIKE %?5%)", nativeQuery = true)
        Page<PaseoEntity> findByTipopaseoIdAndUsuarioIdAndPerroIdAndFechaContainingOrLugarContaining(Long lTipopaseo,
                        Long lUsuario, Long lPerro, String fecha, String lugar, Pageable oPageable);
                        
}

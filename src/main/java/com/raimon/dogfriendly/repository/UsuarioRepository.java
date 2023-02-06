package com.raimon.dogfriendly.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.raimon.dogfriendly.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository <UsuarioEntity, Long>{
    
    UsuarioEntity findByLoginAndPassword(String login, String password);
    UsuarioEntity findByLogin(String strUsuarioName);
    
    boolean existsByLogin(String login);

    @Query(value = "SELECT * FROM usuario WHERE id_tipousuario = ?1 AND (nombre LIKE %?3% OR apellido1 LIKE %?4% OR apellido2 LIKE %?5%)", nativeQuery = true)
    Page<UsuarioEntity> findByTipousuarioIdAndNombreIgnoreCaseContainingOrApellido1IgnoreCaseContainingOrApellido2IgnoreCaseContaining(Long filtertype, String nombre, String apellido1, String apellido2, Pageable oPageable);

    Page<UsuarioEntity> findByNombreIgnoreCaseContainingOrApellido1IgnoreCaseContainingOrApellido2IgnoreCaseContaining(String nombre, String apellido1, String apellido2, Pageable oPageable);

    Page<UsuarioEntity> findByTipousuarioId(Long tipousuario, Pageable oPageable);

}

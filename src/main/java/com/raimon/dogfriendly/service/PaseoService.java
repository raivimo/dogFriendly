package com.raimon.dogfriendly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.raimon.dogfriendly.entity.PaseoEntity;
import com.raimon.dogfriendly.exception.ResourceNotFoundException;
import com.raimon.dogfriendly.exception.ResourceNotModifiedException;
import com.raimon.dogfriendly.helper.ValidationHelper;
import com.raimon.dogfriendly.repository.PaseoRepository;

@Service
public class PaseoService {
    @Autowired
    PaseoRepository oPaseoRepository;

    @Autowired
    AuthService oAuthService;

    public void validate(Long id) {
        if (!oPaseoRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public Long count() {
        // oAuthService.OnlyAdmins();
        return oPaseoRepository.count();
    }

    public PaseoEntity get(Long id) {
        return oPaseoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team with id: " + id + " not found"));
    }

    public Long create(PaseoEntity oNewPaseoEntity) {
        /*
         * oAuthService.OnlyAdmins();
         * validate(oNewPaseoEntity);
         */
        oNewPaseoEntity.setId(0L);
        return oPaseoRepository.save(oNewPaseoEntity).getId();
    }

    public Long update(PaseoEntity oPaseoEntity) {
        validate(oPaseoEntity.getId());
        // oAuthService.OnlyAdmins();
        PaseoEntity oOldPaseoEntity = oPaseoRepository.getReferenceById(oPaseoEntity.getId());
        return oPaseoRepository.save(oPaseoEntity).getId();
    }

    public Long delete(Long id) {
        /* oAuthService.OnlyAdmins(); */
        validate(id);
        oPaseoRepository.deleteById(id);
        if (oPaseoRepository.existsById(id)) {
            throw new ResourceNotModifiedException("can't remove register " + id);
        } else {
            return id;
        }
    }

    public Page<PaseoEntity> getPage(Pageable oPageable, String strFilter, Long id_tipopaseo, Long id_usuario, Long id_perro) {
        /* oAuthService.OnlyAdmins(); */
        ValidationHelper.validateRPP(oPageable.getPageSize());
        Page<PaseoEntity> oPage = null;

        if (id_tipopaseo == null && id_usuario == null && id_perro != null) {
            if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
                oPage = oPaseoRepository.findByPerroId(id_perro, oPageable);
            }
            oPage = oPaseoRepository.findByPerroIdAndFechaContainingOrLugarContaining(id_perro, strFilter,
                    strFilter, oPageable);
        }
        // Pasando id_emisor y id_receptor
        if (id_tipopaseo == null && id_usuario != null && id_perro != null) {
            if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
                oPage = oPaseoRepository.findByUsuarioIdAndPerroId(id_perro,
                        id_usuario, oPageable);
            }
            oPage = oPaseoRepository
                    .findByUsuarioIdAndPerroIdAndFechaContainingOrLugarContaining(id_usuario, id_perro, strFilter,
                            strFilter, oPageable);
        }
            // Pasando id_emisor, id_receptor y id_tipocuenta
            if (id_tipopaseo != null && id_usuario != null && id_perro != null) {
                if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
                    oPage = oPaseoRepository.findByTipopaseoIdAndUsuarioIdAndPerroId(
                            id_tipopaseo, id_usuario, id_perro, oPageable);
                }
                oPage = oPaseoRepository
                        .findByTipopaseoIdAndUsuarioIdAndPerroIdAndFechaContainingOrLugarContaining(
                                id_tipopaseo, id_usuario, id_perro, strFilter, strFilter, oPageable);
            }
            // Pasando solo id_tipopaseo
            if (id_tipopaseo != null && id_usuario == null && id_perro == null) {
                if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
                    oPage = oPaseoRepository.findByTipopaseoId(id_tipopaseo, oPageable);
                }
                oPage = oPaseoRepository.findByTipopaseoIdAndFechaContainingOrLugarContaining(id_tipopaseo,
                        strFilter, strFilter, oPageable);
            }

            // Pasando id_tipopaseo y id_usuario
            if (id_tipopaseo != null && id_usuario != null && id_perro == null) {
                if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
                    oPage = oPaseoRepository.findByTipopaseoIdAndUsuarioId(id_tipopaseo,
                            id_usuario, oPageable);
                }
                oPage = oPaseoRepository.findByTipopaseoIdAndUsuarioIdAndFechaOrLugarContaining(
                        id_tipopaseo, id_usuario, strFilter, strFilter, oPageable);
            }

            // Pasando solo id_usuario
            if (id_tipopaseo == null && id_usuario != null && id_perro == null) {
                if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
                    oPage = oPaseoRepository.findByUsuarioId(id_usuario, oPageable);
                }
                oPage = oPaseoRepository.findByUsuarioIdAndFechaOrLugarContaining(id_usuario,
                        strFilter, strFilter, oPageable);
            }

            // Pasando id_usuario y id_perro
            if (id_tipopaseo == null && id_usuario != null && id_perro != null) {

                if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
                    oPage = oPaseoRepository.findByUsuarioIdAndPerroId(id_usuario,
                            id_perro, oPageable);
                }
                oPage = oPaseoRepository
                        .findByUsuarioIdAndPerroIdAndFechaOrLugarContaining(id_usuario,
                                id_perro, strFilter, strFilter, oPageable);
            }

            // Pasando nada
            if (id_tipopaseo == null && id_usuario == null && id_perro == null) {

                if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
                    oPage = oPaseoRepository.findAll(oPageable);
                }
                oPage = oPaseoRepository.findByFechaOrLugarContaining(strFilter, strFilter, oPageable);

            }
            return oPage;
        }
    }



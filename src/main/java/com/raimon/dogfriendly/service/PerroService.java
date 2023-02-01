package com.raimon.dogfriendly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.raimon.dogfriendly.entity.PerroEntity;
import com.raimon.dogfriendly.exception.CannotPerformOperationException;
import com.raimon.dogfriendly.exception.ResourceNotFoundException;
import com.raimon.dogfriendly.exception.ResourceNotModifiedException;
import com.raimon.dogfriendly.helper.RandomHelper;
import com.raimon.dogfriendly.helper.ValidationHelper;
import com.raimon.dogfriendly.repository.PerroRepository;

@Service
public class PerroService {

    @Autowired
    PerroRepository oPerroRepository;

    @Autowired
    AuthService oAuthService;
    
    public void validate(Long id) {
        if (!oPerroRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public PerroEntity get(Long id) {
        return oPerroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team with id: " + id + " not found"));
    }

    public Long create(PerroEntity oNewPerroEntity) {
       /*  oAuthService.OnlyAdmins(); */
        //validate(oNewUsuarioEntity);
        oNewPerroEntity.setId(0L);
        return oPerroRepository.save(oNewPerroEntity).getId();
    }

    public Long count() {
        // oAuthService.OnlyAdmins();
        return oPerroRepository.count();
    }

    public Long update(PerroEntity oPerroEntity) {
        validate(oPerroEntity.getId());
        // oAuthService.OnlyAdmins();
        PerroEntity oOldPerroEntity = oPerroRepository.getReferenceById(oPerroEntity.getId());
        return oPerroRepository.save(oPerroEntity).getId();
    }

    public Long delete(Long id) {
     /*    oAuthService.OnlyAdmins(); */
        validate(id);
        oPerroRepository.deleteById(id);
        if (oPerroRepository.existsById(id)) {
            throw new ResourceNotModifiedException("can't remove register " + id);
        } else {
            return id;
        }
    }

    public PerroEntity getOneRandom() {
        if (count() > 0) {
            PerroEntity oPerroEntity = null;
            int iPosicion = RandomHelper.getRandomInt(0, (int) oPerroRepository.count() - 1);
            Pageable oPageable = PageRequest.of(iPosicion, 1);
            Page<PerroEntity> perroPage = oPerroRepository.findAll(oPageable);
            List<PerroEntity> perroList = perroPage.getContent();
            oPerroEntity = oPerroRepository.getReferenceById(oPerroEntity.getId());
            return oPerroEntity;
        } else {
            throw new CannotPerformOperationException("ho hay usuarios en la base de datos");
        }
    }

    public Page<PerroEntity> getPage(Pageable oPageable, String strFilter, Long lUsuario, Long lRaza) {
        /* oAuthService.OnlyAdminsOrUsers(); */
        ValidationHelper.validateRPP(oPageable.getPageSize());
        Page<PerroEntity> oPage = null;
        /* if (oAuthService.isAdmin()) { */
       /*      if (lUsuario != null) {
                if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
                    oPage = oPerroRepository.findByUsuarioId(lUsuario, oPageable);
                } else {
                    oPage = oPerroRepository.findByUsuarioIdAndNombreContainingOrFechaContainingOrGeneroContainingOrPesoContainingOrSociableContainingOrPuedeIrSueltoContainingOrEsJuguetonContaining(lUsuario, strFilter, strFilter, strFilter, strFilter, strFilter, strFilter, strFilter, oPageable);
                }
            } else if (lRaza != null) {
                if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
                    oPage = oPerroRepository.findByRazaId(lRaza, oPageable);
                } else {
                    oPage = oPerroRepository.findByRazaIdAndNombreContainingOrFechaContainingOrGeneroContainingOrPesoContainingOrSociableContainingOrPuedeIrSueltoContainingOrEsJuguetonContaining(lRaza, strFilter, strFilter, strFilter, strFilter, strFilter, strFilter, strFilter, oPageable);
                }
            } else { */
                if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
                    oPage = oPerroRepository.findAll(oPageable);
                } else {
                    oPage =  oPerroRepository.findByUsuarioIdAndNombreContainingOrFechaContainingOrGeneroContainingOrPesoContainingOrSociableContainingOrPuedeIrSueltoContainingOrEsJuguetonContaining(lUsuario, strFilter, strFilter, strFilter, strFilter, strFilter, strFilter, strFilter, oPageable);
                }
            /* } */
         /* else {
            if (lUsuario != null) {
                if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
                    oPage = oPerroRepository.findByUsuarioIdAndRazaId(lUsuario, oAuthService.getUserID(), oPageable);
                } else {
                    oPage = oCompraRepository.findByFacturaIdAndUsuarioIdAndCantidadContainingOrPrecioContainingOrFechaContainingOrDescuento_usuarioContainingOrDescuento_productoContaining(lFactura, oAuthService.getUserID(), strFilter, strFilter, strFilter, strFilter, strFilter, oPageable);
                }
            } else if (lRaza != null) {
                if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
                    oPage = oPerroRepository.findByUsuarioIdAndRazaId(lUsuario, oAuthService.getUserID(), oPageable);
                } else {
                    oPage = oCompraRepository.findByProductoIdAndUsuarioIdAndCantidadContainingOrPrecioContainingOrFechaContainingOrDescuento_usuarioContainingOrDescuento_productoContaining(lProducto, oAuthService.getUserID(), strFilter, strFilter, strFilter, strFilter, strFilter, oPageable);
                }
            } else {
                if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
                    oPage = oPerroRepository.findByUsuarioId(oAuthService.getUserID(), oPageable);
                } else {
                    oPage = oCompraRepository.findByUsuarioIdAndCantidadContainingOrPrecioContainingOrFechaContainingOrDescuento_usuarioContainingOrDescuento_productoContaining(oAuthService.getUserID(), strFilter, strFilter, strFilter, strFilter, strFilter, oPageable);
                }
            }
        } */
        return oPage;
    }



}

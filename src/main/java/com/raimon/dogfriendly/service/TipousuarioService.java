package com.raimon.dogfriendly.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.raimon.dogfriendly.entity.TipousuarioEntity;
import com.raimon.dogfriendly.exception.CannotPerformOperationException;
import com.raimon.dogfriendly.exception.ResourceNotFoundException;
import com.raimon.dogfriendly.exception.ResourceNotModifiedException;
import com.raimon.dogfriendly.helper.RandomHelper;
import com.raimon.dogfriendly.helper.ValidationHelper;
import com.raimon.dogfriendly.repository.TipousuarioRepository;

@Service
public class TipousuarioService {

    private final List<String> TiposDeUsuario = List.of("Administrador", "Usuario", "Invitado", "Paseador", "Voluntario");

    @Autowired
    TipousuarioRepository oTipousuarioRepository;

    public void validate(Long id) {
        if (!oTipousuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public TipousuarioEntity get(Long id) {
        return oTipousuarioRepository.getReferenceById(id);
    }

    public Long create(TipousuarioEntity oNewTipoTipousuarioEntity) {
        // oAuthService.OnlyAdmins();
        return oTipousuarioRepository.save(oNewTipoTipousuarioEntity).getId();
    }

    public Long count() {
        // oAuthService.OnlyAdmins();
        return oTipousuarioRepository.count();
    }

    public Long update(TipousuarioEntity oTipoTipousuarioEntity) {
        // oAuthService.OnlyAdmins();
        validate(oTipoTipousuarioEntity.getId());
        oTipousuarioRepository.save(oTipoTipousuarioEntity);
        return oTipoTipousuarioEntity.getId();
    }

    public Long delete(Long id) {
        // oAuthService.OnlyAdmins();
        validate(id);
        oTipousuarioRepository.deleteById(id);
        if (oTipousuarioRepository.existsById(id)) {
            throw new ResourceNotModifiedException("can't remove register " + id);
        } else {
            return id;
        }
    }

    public TipousuarioEntity getOneRandom() {
        if (count() > 0) {
            TipousuarioEntity oTipoTipousuarioEntity = null;
            int iPosicion = RandomHelper.getRandomInt(0, (int) oTipousuarioRepository.count() - 1);
            Pageable oPageable = PageRequest.of(iPosicion, 1);
            Page<TipousuarioEntity> TipoUsuarioPage = oTipousuarioRepository.findAll(oPageable);
            TipoUsuarioPage.getContent();
            oTipoTipousuarioEntity = oTipousuarioRepository.getReferenceById(oTipoTipousuarioEntity.getId());
            return oTipoTipousuarioEntity;
        } else {
            throw new CannotPerformOperationException("ho hay usuarios en la base de datos");
        }
    }

    public Page<TipousuarioEntity> getPage(Pageable oPageable, String strFilter) {
        // oAuthService.OnlyAdmins();
        ValidationHelper.validateRPP(oPageable.getPageSize());
        Page<TipousuarioEntity> oPage = null;
            if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
                oPage = oTipousuarioRepository.findAll(oPageable);
            } else {
                oPage = oTipousuarioRepository
                        .findByNombre(strFilter, oPageable);
            }
            return oPage;
        }

        private TipousuarioEntity generateTipoUsuario() {
            TipousuarioEntity oTipousuarioEntity = new TipousuarioEntity();
            oTipousuarioEntity.setNombre(TiposDeUsuario.get(RandomHelper.getRandomInt(0, TiposDeUsuario.size() - 1)));
            return oTipousuarioEntity;
        }
    
        public TipousuarioEntity generateOne() {
            // oAuthService.OnlyAdmins();
            return oTipousuarioRepository.save(generateTipoUsuario());
        }
    
        public Long generateSome(Long amount) {
            // oAuthService.OnlyAdmins();
            List<TipousuarioEntity> usuarioToSave = new ArrayList<>();
            for (int i = 0; i < amount; i++) {
                usuarioToSave.add(generateTipoUsuario());
            }
            oTipousuarioRepository.saveAll(usuarioToSave);
            return oTipousuarioRepository.count();
        }















        
}

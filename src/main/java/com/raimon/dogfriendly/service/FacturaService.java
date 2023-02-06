package com.raimon.dogfriendly.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.raimon.dogfriendly.entity.FacturaEntity;
import com.raimon.dogfriendly.entity.PaseoEntity;
import com.raimon.dogfriendly.exception.ResourceNotFoundException;
import com.raimon.dogfriendly.exception.ResourceNotModifiedException;
import com.raimon.dogfriendly.helper.RandomHelper;
import com.raimon.dogfriendly.helper.ValidationHelper;
import com.raimon.dogfriendly.repository.FacturaRepository;
import com.raimon.dogfriendly.repository.PaseoRepository;

@Service
public class FacturaService {

    @Autowired
    FacturaRepository oFacturaRepository;

    @Autowired
    PaseoRepository oPaseoRepository;

    @Autowired
    AuthService oAuthService;

    public void validate(Long id) {
        if (!oFacturaRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public Long count() {
        // oAuthService.OnlyAdmins();
        return oFacturaRepository.count();
    }

    public FacturaEntity get(Long id) {
        return oFacturaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team with id: " + id + " not found"));
    }

    public Long create(FacturaEntity oNewFacturaEntity) {
        /* oAuthService.OnlyAdmins(); */
        // validate(oNewUsuarioEntity);
        oNewFacturaEntity.setId(0L);
        return oFacturaRepository.save(oNewFacturaEntity).getId();
    }

    public Long update(FacturaEntity oFacturaEntity) {
        validate(oFacturaEntity.getId());
        // oAuthService.OnlyAdmins();
        FacturaEntity oOldFacturaEntity = oFacturaRepository.getReferenceById(oFacturaEntity.getId());
        return oFacturaRepository.save(oFacturaEntity).getId();
    }

    public Long delete(Long id) {
        /* oAuthService.OnlyAdmins(); */
        validate(id);
        oFacturaRepository.deleteById(id);
        if (oFacturaRepository.existsById(id)) {
            throw new ResourceNotModifiedException("can't remove register " + id);
        } else {
            return id;
        }
    }

    public Page<FacturaEntity> getPage(Pageable oPageable, String strFilter, Long lPaseo) {
        // oAuthService.OnlyAdmins();
        ValidationHelper.validateRPP(oPageable.getPageSize());
        Page<FacturaEntity> oPage = null;
        if (lPaseo == null) {
            if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
                oPage = oFacturaRepository.findAll(oPageable);
            } else {
                oPage = oFacturaRepository
                        .findByFechaIgnoreCaseContainingOrIvaIgnoreCaseContainingOrPagadoIgnoreCaseContaining(
                                strFilter, strFilter, strFilter, oPageable);
            }
        } else {
            if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
                oPage = oFacturaRepository.findByPaseoId(lPaseo, oPageable);
            } else {
                oPage = oFacturaRepository
                        .findByPaseoIdAndFechaIgnoreCaseContainingOrIvaIgnoreCaseContainingOrPagadoIgnoreCaseContaining(
                                lPaseo, strFilter, strFilter, strFilter, oPageable);
            }
        }
        return oPage;
    }

    private FacturaEntity generateFactura() {
        FacturaEntity oFacturaEntity = new FacturaEntity();
        oFacturaEntity.setFecha(RandomHelper.getRandomLocalDate());
        oFacturaEntity.setIva(21);
        oFacturaEntity.setPagado(RandomHelper.getRandomBoolean());

        List<PaseoEntity> allPaseos = oPaseoRepository.findAll();
        PaseoEntity randomPaseo = allPaseos.get(RandomHelper.getRandomInt(0, allPaseos.size() - 1));
        oFacturaEntity.setPaseo(randomPaseo);

        return oFacturaEntity;
    }

    public FacturaEntity generateOne() {
        // oAuthService.OnlyAdmins();
        return oFacturaRepository.save(generateFactura());
    }

    public Long generateSome(Long amount) {
        // oAuthService.OnlyAdmins();
        List<FacturaEntity> facturaToSave = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            facturaToSave.add(generateFactura());
        }
        oFacturaRepository.saveAll(facturaToSave);
        return oFacturaRepository.count();
    }

}

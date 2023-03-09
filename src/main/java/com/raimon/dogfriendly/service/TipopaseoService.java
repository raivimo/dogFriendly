package com.raimon.dogfriendly.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.raimon.dogfriendly.entity.TipopaseoEntity;
import com.raimon.dogfriendly.exception.ResourceNotFoundException;
import com.raimon.dogfriendly.exception.ResourceNotModifiedException;
import com.raimon.dogfriendly.helper.RandomHelper;
import com.raimon.dogfriendly.helper.ValidationHelper;
import com.raimon.dogfriendly.repository.TipopaseoRepository;

@Service
public class TipopaseoService {

    private final List<String> nombrePaseo = List.of("Básico", "Extendido", "Juguetón", "Instructivo", "Sociable");

    @Autowired
    TipopaseoRepository oTipopaseoRepository;

    @Autowired
    AuthService oAuthService;

    public void validate(Long id) {
        if (!oTipopaseoRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public Long count() {
        // oAuthService.OnlyAdmins();
        return oTipopaseoRepository.count();
    }

    public TipopaseoEntity get(Long id) {
        return oTipopaseoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team with id: " + id + " not found"));
    }

    public Long create(TipopaseoEntity oNewTipopaseoEntity) {
        /* oAuthService.OnlyAdmins(); */
        // validate(oNewUsuarioEntity);
        oNewTipopaseoEntity.setId(0L);
        return oTipopaseoRepository.save(oNewTipopaseoEntity).getId();
    }

    public Long update(TipopaseoEntity oTipopaseoEntity) {
        validate(oTipopaseoEntity.getId());
        oTipopaseoRepository.getReferenceById(oTipopaseoEntity.getId());
        return oTipopaseoRepository.save(oTipopaseoEntity).getId();
    }

    public Long delete(Long id) {
        /* oAuthService.OnlyAdmins(); */
        validate(id);
        oTipopaseoRepository.deleteById(id);
        if (oTipopaseoRepository.existsById(id)) {
            throw new ResourceNotModifiedException("can't remove register " + id);
        } else {
            return id;
        }
    }

    public Page<TipopaseoEntity> getPage(Pageable oPageable, String strFilter) {
        // oAuthService.OnlyAdmins();
        ValidationHelper.validateRPP(oPageable.getPageSize());
        Page<TipopaseoEntity> oPage = null;
        if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
            oPage = oTipopaseoRepository.findAll(oPageable);
        } else {
            oPage = oTipopaseoRepository
                    .findByNombre(strFilter, oPageable);
        }
        return oPage;
    }

    private TipopaseoEntity generateTipoPaseo() {
        TipopaseoEntity oTipopaseoEntity = new TipopaseoEntity();

        oTipopaseoEntity.setNombre(nombrePaseo.get(RandomHelper.getRandomInt(0, nombrePaseo.size() - 1)));
        oTipopaseoEntity.setDuracion(RandomHelper.getRandomInt(10, 120));
        return oTipopaseoEntity;
    }

    public TipopaseoEntity generateOne() {
        // oAuthService.OnlyAdmins();
        return oTipopaseoRepository.save(generateTipoPaseo());
    }

    public Long generateSome(Long amount) {
        // oAuthService.OnlyAdmins();
        List<TipopaseoEntity> tipoPaseoToSave = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            tipoPaseoToSave.add(generateTipoPaseo());
        }
        oTipopaseoRepository.saveAll(tipoPaseoToSave);
        return oTipopaseoRepository.count();
    }

}

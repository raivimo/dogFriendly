package com.raimon.dogfriendly.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.raimon.dogfriendly.entity.RazaEntity;
import com.raimon.dogfriendly.exception.ResourceNotFoundException;
import com.raimon.dogfriendly.exception.ResourceNotModifiedException;
import com.raimon.dogfriendly.exception.ValidationException;
import com.raimon.dogfriendly.helper.RandomHelper;
import com.raimon.dogfriendly.helper.ValidationHelper;
import com.raimon.dogfriendly.repository.RazaRepository;

@Service
public class RazaService {

    private final List<String> nombreRazas = List.of("Braco Dupuy", "Braco Italiano", "Broholmer", "Buhund Noruego",
            "Bull terrier", " Bulldog americano", "Bulldog inglés", "Bulldog francés", "Bullmastiff", "Ca de Bestiar",
            "Cairn terrier",
            "Cane Corso", "Cane da Pastore Maremmano-Abruzzese", "Caniche (Poodle)", "Caniche Toy", " Cesky Terrier",
            "Chesapeake Bay Retriever", "Chihuahua", "Chin", "Dálmata", "Dobermann", " Dogo Argentino",
            "Dogo de Burdeos");

    private final List<String> tamanyos = List.of("Grande", "Mediano", "Pequeño", "Enano");

    @Autowired
    RazaRepository oRazaRepository;

    @Autowired
    AuthService oAuthService;

    public void validate(Long id) {
        if (!oRazaRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public void validate(RazaEntity oRazaEntity) {
        ValidationHelper.validateStringLength(oRazaEntity.getNombre(), 2, 20,
                "campo name de Usuario(el campo debe tener longitud de 2 a 20 caracteres)");
        ValidationHelper.validateStringLength(oRazaEntity.getTamanyo(), 2, 20,
                "campo surname de Developer(el campo debe tener longitud de 2 a 20 caracteres)");
        if (oRazaRepository.existsById(oRazaEntity.getId() ) ) {
            throw new ValidationException("el campo login está repetido");
        }
    }

    public RazaEntity get(Long id) {
        return oRazaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team with id: " + id + " not found"));
    }

    public Long create(RazaEntity oNewRazaEntity) {
        // oAuthService.OnlyAdmins();
        validate(oNewRazaEntity);
        return oRazaRepository.save(oNewRazaEntity).getId();
    }

    public Long count() {
        // oAuthService.OnlyAdmins();
        return oRazaRepository.count();
    }

    public Long update(RazaEntity oRazaEntity) {
        // oAuthService.OnlyAdmins();
        validate(oRazaEntity.getId());
        oRazaRepository.getReferenceById(oRazaEntity.getId());
        return oRazaRepository.save(oRazaEntity).getId();
    }

    public Long delete(Long id) {
        /* oAuthService.OnlyAdmins(); */
        validate(id);
        oRazaRepository.deleteById(id);
        if (oRazaRepository.existsById(id)) {
            throw new ResourceNotModifiedException("can't remove register " + id);
        } else {
            return id;
        }
    }

    public Page<RazaEntity> getPage(Pageable oPageable, String strFilter) {
        // oAuthService.OnlyAdmins();
        ValidationHelper.validateRPP(oPageable.getPageSize());
        Page<RazaEntity> oPage = null;
        if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
            oPage = oRazaRepository.findAll(oPageable);
        } else {
            oPage = oRazaRepository
                    .findByNombre(strFilter, oPageable);
        }
        return oPage;
    }

    private RazaEntity generateRaza() {
        RazaEntity oRazaEntity = new RazaEntity();
        oRazaEntity.setNombre(nombreRazas.get(RandomHelper.getRandomInt(0, nombreRazas.size() - 1)));
        oRazaEntity.setTamanyo(tamanyos.get(RandomHelper.getRandomInt(0, tamanyos.size() - 1)));
        return oRazaEntity;
    }

    public RazaEntity generateOne() {
        // oAuthService.OnlyAdmins();
        return oRazaRepository.save(generateRaza());
    }

    public Long generateSome(Long amount) {
        // oAuthService.OnlyAdmins();
        List<RazaEntity> razaToSave = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            razaToSave.add(generateRaza());
        }
        oRazaRepository.saveAll(razaToSave);
        return oRazaRepository.count();
    }

}

package com.raimon.dogfriendly.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.raimon.dogfriendly.entity.UsuarioEntity;
import com.raimon.dogfriendly.entity.PerroEntity;
import com.raimon.dogfriendly.entity.RazaEntity;
import com.raimon.dogfriendly.exception.CannotPerformOperationException;
import com.raimon.dogfriendly.exception.ResourceNotFoundException;
import com.raimon.dogfriendly.exception.ResourceNotModifiedException;
import com.raimon.dogfriendly.helper.RandomHelper;
import com.raimon.dogfriendly.helper.ValidationHelper;
import com.raimon.dogfriendly.repository.PerroRepository;
import com.raimon.dogfriendly.repository.RazaRepository;
import com.raimon.dogfriendly.repository.UsuarioRepository;

@Service
public class PerroService {

    private final List<String> nombreMascota = List.of("Thor", "Rocco","Baloo","Nana","Chucky","Potter","Rei","Dama","Hulk", "Jack","Uggie", "Dante", "Conan", "Luna",
    "Akira", "Molly", "Thanos", "Beethoven", "Frank", "Golfo", "Dumbo", "Merlín", "Vader", "Penny", "Rocky", "Brian", "Verdell", "Reina", "Dino", "Troya" );

    @Autowired
    PerroRepository oPerroRepository;

    @Autowired
    UsuarioRepository oUsuarioRepository;

    @Autowired
    RazaRepository oRazaRepository;

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
        /* oAuthService.OnlyAdmins(); */
        // validate(oNewUsuarioEntity);
        oNewPerroEntity.setId(0L);
        return oPerroRepository.save(oNewPerroEntity).getId();
    }

    public Long count() {
        // oAuthService.OnlyAdmins();
        return oPerroRepository.count();
    }

    public Long update(PerroEntity oPerroEntity) {
        validate(oPerroEntity.getId());
        oPerroRepository.getReferenceById(oPerroEntity.getId());
        return oPerroRepository.save(oPerroEntity).getId();
    }

    public Long delete(Long id) {
        /* oAuthService.OnlyAdmins(); */
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
            perroPage.getContent();
            oPerroEntity = oPerroRepository.getReferenceById(oPerroEntity.getId());
            return oPerroEntity;
        } else {
            throw new CannotPerformOperationException("ho hay usuarios en la base de datos");
        }
    }

    public List<PerroEntity> getListPerrosUsuario(Long id_usuario){
        return oPerroRepository.findListPerrosUsuario(id_usuario);
    }

    public Page<PerroEntity> getPage(Pageable oPageable, String strFilter, Long id_usuario, Long id_raza) {
        // oAuthService.OnlyAdmins();
        ValidationHelper.validateRPP(oPageable.getPageSize());
        Page<PerroEntity> oPage = null;
        // Pasar id_usuario
        if (id_raza == null && id_usuario != null) {
            if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
                oPage = oPerroRepository.findByUsuarioId(id_usuario, oPageable);
            }
            oPage = oPerroRepository.findByUsuarioIdAndNombreIgnoreCaseContainingOrFechaNacimientoIgnoreCaseContaining(
                    id_usuario, strFilter, strFilter, oPageable);
        }
        // Pasar id_raza
        if (id_raza != null && id_usuario == null) {
            if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
                oPage = oPerroRepository.findByRazaId(id_raza, oPageable);
            }
            oPage = oPerroRepository.findByRazaIdAndNombreIgnoreCaseContainingOrFechaNacimientoIgnoreCaseContaining(
                    id_raza, strFilter, strFilter, oPageable);
        }
        // Pasando todo
        if (id_raza != null && id_usuario != null) {
            if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
                oPage = oPerroRepository.findByRazaIdAndUsuarioId(id_raza, id_usuario, oPageable);
            }
            oPage = oPerroRepository.findByRazaIdAndUsuarioIdAndNombreIgnoreCaseContainingOrFechaNacimiento(id_raza,
                    id_usuario, strFilter, strFilter, oPageable);
        }
        // Pasando nada
        if (id_raza == null && id_usuario == null) {
            if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
                oPage = oPerroRepository.findAll(oPageable);
            }
            oPage = oPerroRepository.findByNombreIgnoreCaseContaining(strFilter, oPageable);
        }
        return oPage;
    }


    private PerroEntity generatePerro() {
        PerroEntity oPerroEntity = new PerroEntity();

        oPerroEntity.setNombre(nombreMascota.get(RandomHelper.getRandomInt(0, nombreMascota.size() - 1)));
        oPerroEntity.setFechaNacimiento(RandomHelper.getRandomLocalDate());
        oPerroEntity.setGenero(RandomHelper.getRandomInt(0, 1));
        oPerroEntity.setPeso(RandomHelper.getRandomInt2(3, 45));
        oPerroEntity.setSociable(RandomHelper.getRandomBoolean());
        oPerroEntity.setPuedeIrSuelto(RandomHelper.getRandomBoolean());
        oPerroEntity.setEsJugueton(RandomHelper.getRandomBoolean());

        List<UsuarioEntity> allUsuarios = oUsuarioRepository.findAll();
        UsuarioEntity randomUsuario = allUsuarios.get(RandomHelper.getRandomInt(0, allUsuarios.size() - 1));
        oPerroEntity.setUsuario(randomUsuario);
    
        List<RazaEntity> allRazas = oRazaRepository.findAll();
        RazaEntity randomRaza = allRazas.get(RandomHelper.getRandomInt(0, allRazas.size() - 1));
        oPerroEntity.setRazas(randomRaza);

        return oPerroEntity;
    }

    public PerroEntity generateOne() {
        // oAuthService.OnlyAdmins();
        return oPerroRepository.save(generatePerro());
    }

    public Long generateSome(Long amount) {
        // oAuthService.OnlyAdmins();
        List<PerroEntity> perroToSave = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            perroToSave.add(generatePerro());
        }
        oPerroRepository.saveAll(perroToSave);
        return oPerroRepository.count();
    }

}

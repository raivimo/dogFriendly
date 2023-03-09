package com.raimon.dogfriendly.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.raimon.dogfriendly.entity.UsuarioEntity;
import com.raimon.dogfriendly.exception.CannotPerformOperationException;
import com.raimon.dogfriendly.exception.ResourceNotFoundException;
import com.raimon.dogfriendly.exception.ResourceNotModifiedException;
import com.raimon.dogfriendly.exception.ValidationException;
import com.raimon.dogfriendly.helper.RandomHelper;
import com.raimon.dogfriendly.helper.TipoUsuarioHelper;
import com.raimon.dogfriendly.helper.ValidationHelper;
import com.raimon.dogfriendly.repository.TipousuarioRepository;
import com.raimon.dogfriendly.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final String DOGFRIENDLY_DEFAULT_PASSWORD = "73ec8dee81ea4c9e7515d63c9e5bbb707c7bc4789363c5afa401d3aa780630f6";
    private final List<String> names = List.of("Ainhoa", "Kevin", "Estefania", "Cristina",
            "Jose Maria", "Lucas Ezequiel", "Carlos", "Elliot", "Alexis", "Ruben", "Luis Fernando", "Karim", "Luis",
            "Jose David", "Nerea", "Ximo", "Iris", "Alvaro", "Mario", "Raimon");

    private final List<String> surnames = List.of("Benito", "Blanco", "Boriko", "Carrascosa", "Guerrero", "Gyori",
            "Lazaro", "Luque", "Perez", "Perez", "Perez", "Rezgaoui", "Rodríguez", "Rosales", "Soler", "Soler", "Suay",
            "Talaya", "Tomas", "Vilar");

    private final List<String> last_names = List.of("Sanchis", "Bañuls", "Laenos", "Torres", "Sanchez", "Gyori",
            "Luz", "Pascual", "Blayimir", "Castello", "Hurtado", "Mourad", "Fernández", "Ríos", "Benavent", "Benavent",
            "Patricio", "Romance", "Zanon", "Morera");

    @Autowired
    UsuarioRepository oUsuarioRepository;

    @Autowired
    TipousuarioService oTipousuarioService;

    @Autowired
    TipousuarioRepository oTipousuarioRepository;

    @Autowired
    AuthService oAuthService;

    // -- Start Validation --
    public void validate(Long id) {
        if (!oUsuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not exist");
        }
    }

    public void validate(UsuarioEntity oUsuarioEntity) {
        ValidationHelper.validateStringLength(oUsuarioEntity.getNombre(), 2, 20,
                "campo name de Usuario(el campo debe tener longitud de 2 a 20 caracteres)");
        ValidationHelper.validateStringLength(oUsuarioEntity.getApellido1(), 2, 20,
                "campo surname de Developer(el campo debe tener longitud de 2 a 20 caracteres)");
        ValidationHelper.validateStringLength(oUsuarioEntity.getApellido2(), 2, 20,
                "campo lastname de Developer(el campo debe tener longitud de 2 a 20 caracteres)");
        ValidationHelper.validateEmail(oUsuarioEntity.getEmail(), "campo email de Usuario");
        ValidationHelper.validateLogin(oUsuarioEntity.getLogin(), "campo login de Usuario");
        if (oUsuarioRepository.existsById(oUsuarioEntity.getId() ) ) {
            throw new ValidationException("el campo login está repetido");
        }
        oTipousuarioService.validate(oUsuarioEntity.getTipousuario().getId());
    }
    // -- End Validation --

    public UsuarioEntity get(Long id) {
        return oUsuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario with id: " + id + " not found"));
    }

    public Long create(UsuarioEntity oNewUsuarioEntity) {
       /*  oAuthService.OnlyAdmins();
        validate(oNewUsuarioEntity); */
        oNewUsuarioEntity.setId(0L);
        oNewUsuarioEntity.setPassword(DOGFRIENDLY_DEFAULT_PASSWORD);
        return oUsuarioRepository.save(oNewUsuarioEntity).getId();
    }

    public Long count() {
        // oAuthService.OnlyAdmins();
        return oUsuarioRepository.count();
    }

    public Long update(UsuarioEntity oUsuarioEntity) {
        validate(oUsuarioEntity.getId());
        // oAuthService.OnlyAdmins();
        UsuarioEntity oOldUsuarioEntity = oUsuarioRepository.getReferenceById(oUsuarioEntity.getId());
        oUsuarioEntity.setPassword(oOldUsuarioEntity.getPassword());
        return oUsuarioRepository.save(oUsuarioEntity).getId();
    }

    public Long delete(Long id) {
        oAuthService.OnlyAdmins();
        validate(id);
        oUsuarioRepository.deleteById(id);
        if (oUsuarioRepository.existsById(id)) {
            throw new ResourceNotModifiedException("can't remove register " + id);
        } else {
            return id;
        }
    }

    public UsuarioEntity getOneRandom() {
        if (count() > 0) {
            UsuarioEntity oUsuarioEntity = null;
            int iPosicion = RandomHelper.getRandomInt(0, (int) oUsuarioRepository.count() - 1);
            Pageable oPageable = PageRequest.of(iPosicion, 1);
            Page<UsuarioEntity> usuarioPage = oUsuarioRepository.findAll(oPageable);
            usuarioPage.getContent();
            oUsuarioEntity = oUsuarioRepository.getReferenceById(oUsuarioEntity.getId());
            return oUsuarioEntity;
        } else {
            throw new CannotPerformOperationException("ho hay usuarios en la base de datos");
        }
    }

    public Page<UsuarioEntity> getPage(Pageable oPageable, String strFilter, Long lTipoUsuario) {
        // oAuthService.OnlyAdmins();
        ValidationHelper.validateRPP(oPageable.getPageSize());
        Page<UsuarioEntity> oPage = null;
        if (lTipoUsuario == null) {
            if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
                oPage = oUsuarioRepository.findAll(oPageable);
            } else {
                oPage = oUsuarioRepository
                        .findByNombreIgnoreCaseContainingOrApellido1IgnoreCaseContainingOrApellido2IgnoreCaseContaining(
                                strFilter, strFilter, strFilter, oPageable);
            }
        } else {
            if (strFilter == null || strFilter.isEmpty() || strFilter.trim().isEmpty()) {
                oPage = oUsuarioRepository.findByTipousuarioId(lTipoUsuario, oPageable);
            } else {
                oPage = oUsuarioRepository
                        .findByTipousuarioIdAndNombreIgnoreCaseContainingOrApellido1IgnoreCaseContainingOrApellido2IgnoreCaseContaining(
                                lTipoUsuario, strFilter, strFilter, strFilter, oPageable);
            }
        }
        return oPage;
    }

    private UsuarioEntity generateUsuario() {
        UsuarioEntity oUsuarioEntity = new UsuarioEntity();

        oUsuarioEntity.setNombre(names.get(RandomHelper.getRandomInt(0, names.size() - 1)));
        oUsuarioEntity.setApellido1(surnames.get(RandomHelper.getRandomInt(0, names.size() - 1)));
        oUsuarioEntity.setApellido2(last_names.get(RandomHelper.getRandomInt(0, names.size() - 1)));
        oUsuarioEntity.setFechaNacimiento(RandomHelper.getRandomLocalDate());
        oUsuarioEntity.setLogin((oUsuarioEntity.getNombre().toLowerCase()));

        oUsuarioEntity.setEmail(oUsuarioEntity.getLogin() + "@dogfriendly.net");
        oUsuarioEntity.setPassword(DOGFRIENDLY_DEFAULT_PASSWORD);

        if (RandomHelper.getRandomInt(0, 10) > 1) {
            oUsuarioEntity.setTipousuario(oTipousuarioRepository.getReferenceById(TipoUsuarioHelper.USER));
        } else {
            oUsuarioEntity.setTipousuario(oTipousuarioRepository.getReferenceById(TipoUsuarioHelper.ADMIN));
        }
        return oUsuarioEntity;
    }

    public UsuarioEntity generateOne() {
        // oAuthService.OnlyAdmins();
        return oUsuarioRepository.save(generateUsuario());
    }

    public Long generateSome(Long amount) {
        // oAuthService.OnlyAdmins();
        List<UsuarioEntity> usuarioToSave = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            usuarioToSave.add(generateUsuario());
        }
        oUsuarioRepository.saveAll(usuarioToSave);
        return oUsuarioRepository.count();
    }


}
package com.raimon.dogfriendly.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.raimon.dogfriendly.bean.UsuarioBean;
import com.raimon.dogfriendly.entity.UsuarioEntity;
import com.raimon.dogfriendly.exception.UnauthorizedException;
import com.raimon.dogfriendly.helper.JwtHelper;
import com.raimon.dogfriendly.helper.TipoUsuarioHelper;
import com.raimon.dogfriendly.repository.UsuarioRepository;

@Service
public class AuthService {

    @Autowired
    HttpSession oHttpSession;

    @Autowired
    private HttpServletRequest oRequest;

    @Autowired
    UsuarioRepository oUsuarioRepository;

    public String login(@RequestBody UsuarioBean oUsuarioBean) {
        if (oUsuarioBean.getPassword() != null) {
            UsuarioEntity oUsuarioEntity = oUsuarioRepository.findByLoginAndPassword(oUsuarioBean.getLogin(), oUsuarioBean.getPassword());
            if (oUsuarioEntity != null) {
                return JwtHelper.generateJWT(oUsuarioBean.getLogin());
            } else {
                throw new UnauthorizedException("login or password incorrect");
            }
        } else {
            throw new UnauthorizedException("wrong password");
        }
    }

    public UsuarioEntity check() {
        String strUsuarioName = (String) oRequest.getAttribute("usuario");
        if (strUsuarioName != null) {
            UsuarioEntity oUsuarioEntity = oUsuarioRepository.findByLogin(strUsuarioName);
            return oUsuarioEntity;
        } else {
            throw new UnauthorizedException("No active session");
        }
    }

    public boolean isAdmin() {
        UsuarioEntity oUsuarioSessionEntity = oUsuarioRepository.findByLogin((String)  oRequest.getAttribute("usuario"));
        if (oUsuarioSessionEntity != null) {
            if (oUsuarioSessionEntity.getTipousuario().getId().equals(TipoUsuarioHelper.ADMIN)) {
                return true;
            }
        }
        return false;
    }

    public void OnlyAdmins() {
        String nombre = (String)  oRequest.getAttribute("usuario");
        UsuarioEntity oUsuarioSessionEntity = oUsuarioRepository.findByLogin(nombre);
        if (oUsuarioSessionEntity == null) {
            throw new UnauthorizedException("La peticion viene nula");
        } else {
            if (!oUsuarioSessionEntity.getTipousuario().getId().equals(TipoUsuarioHelper.ADMIN)) {
                throw new UnauthorizedException("this request is only allowed to admin role");
            }
        }
    }
    
  



  
}

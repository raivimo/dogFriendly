package com.raimon.dogfriendly.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raimon.dogfriendly.bean.UsuarioBean;
import com.raimon.dogfriendly.entity.UsuarioEntity;
import com.raimon.dogfriendly.service.AuthService;

@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    AuthService oAuthService;

    @GetMapping("")
    public ResponseEntity<UsuarioEntity> check() {
        return new ResponseEntity<UsuarioEntity>(oAuthService.check(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsuarioEntity> login(@org.springframework.web.bind.annotation.RequestBody UsuarioBean oUsuarioBean) {
        return new ResponseEntity<UsuarioEntity>(oAuthService.login(oUsuarioBean), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<?> logout() {
        oAuthService.logout();
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}

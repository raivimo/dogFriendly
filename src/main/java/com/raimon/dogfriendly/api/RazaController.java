package com.raimon.dogfriendly.api;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raimon.dogfriendly.entity.FacturaEntity;
import com.raimon.dogfriendly.entity.RazaEntity;
import com.raimon.dogfriendly.service.RazaService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/raza")
public class RazaController {
    
    @Autowired
    RazaService oRazaService;

    @GetMapping("/{id}")
    public ResponseEntity<RazaEntity> get (@PathVariable(value="id") Long id){
        return new ResponseEntity<RazaEntity>(oRazaService.get(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody RazaEntity oNewRazaEntity) {
        return new ResponseEntity<Long>(oRazaService.create(oNewRazaEntity), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Long> update(@RequestBody RazaEntity oRazaEntity) {
        return new ResponseEntity<Long>(oRazaService.update(oRazaEntity), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<Long>(oRazaService.delete(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oRazaService.count(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Page<RazaEntity>> getPage(
            @ParameterObject @PageableDefault(page = 0, size = 5, direction = Sort.Direction.DESC) Pageable oPageable,
            @RequestParam(name = "filter", required = false) String strFilter,
            @RequestParam(name = "tipousuario", required = false) Long lTipoUsuario) {
        return new ResponseEntity<Page<RazaEntity>>(oRazaService.getPage(oPageable, strFilter), HttpStatus.OK);
    }

    @PostMapping("/generate")
    public ResponseEntity<RazaEntity> generateOne() {
        return new ResponseEntity<>(oRazaService.generateOne(), HttpStatus.OK);
    }

    @PostMapping("/generate/{amount}")
    public ResponseEntity<Long> generateSome(@PathVariable Long amount) {
        return new ResponseEntity<>(oRazaService.generateSome(amount), HttpStatus.OK);
    }







    
}

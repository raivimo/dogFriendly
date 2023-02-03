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

import com.raimon.dogfriendly.entity.PaseoEntity;
import com.raimon.dogfriendly.service.PaseoService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/paseo")
public class PaseoController {

    @Autowired
    PaseoService oPaseoService;

    @GetMapping("/{id}")
    public ResponseEntity<PaseoEntity> get(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<PaseoEntity>(oPaseoService.get(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody PaseoEntity oNewPaseoEntity) {
        return new ResponseEntity<Long>(oPaseoService.create(oNewPaseoEntity), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Long> update(@RequestBody PaseoEntity oPaseoEntity) {
        return new ResponseEntity<Long>(oPaseoService.update(oPaseoEntity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<Long>(oPaseoService.delete(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oPaseoService.count(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Page<PaseoEntity>> getPage(
            @ParameterObject @PageableDefault(page = 0, size = 5, direction = Sort.Direction.DESC) Pageable oPageable,
            @RequestParam(name = "filter", required = false) String strFilter,
            @RequestParam(name = "tipopaseo", required = false) Long lTipopaseo,
            @RequestParam(name = "usuario", required = false) Long lUsuario,
            @RequestParam(name = "perro", required = false) Long lPerro) {
        return new ResponseEntity<Page<PaseoEntity>>(oPaseoService.getPage(oPageable, strFilter,  lTipopaseo, lUsuario, lPerro), HttpStatus.OK);
    }

    @PostMapping("/generate")
    public ResponseEntity<PaseoEntity> generateOne() {
        return new ResponseEntity<>(oPaseoService.generateOne(), HttpStatus.OK);
    }

    @PostMapping("/generate/{amount}")
    public ResponseEntity<Long> generateSome(@PathVariable Long amount) {
        return new ResponseEntity<>(oPaseoService.generateSome(amount), HttpStatus.OK);
    }


}

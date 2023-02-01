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
import com.raimon.dogfriendly.service.FacturaService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/factura")

public class FacturaController {

    @Autowired
    FacturaService oFacturaService;

    @GetMapping("/{id}")
    public ResponseEntity<FacturaEntity> get (@PathVariable(value="id") Long id){
        return new ResponseEntity<FacturaEntity>(oFacturaService.get(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody FacturaEntity oNewFacturaEntity) {
        return new ResponseEntity<Long>(oFacturaService.create(oNewFacturaEntity), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Long> update(@RequestBody FacturaEntity oFacturaEntity) {
        return new ResponseEntity<Long>(oFacturaService.update(oFacturaEntity), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<Long>(oFacturaService.delete(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oFacturaService.count(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Page<FacturaEntity>> getPage(
            @ParameterObject @PageableDefault(page = 0, size = 5, direction = Sort.Direction.DESC) Pageable oPageable,
            @RequestParam(name = "filter", required = false) String strFilter,
            @RequestParam(name = "paseo", required = false) Long lPaseo) {
        return new ResponseEntity<Page<FacturaEntity>>(oFacturaService.getPage(oPageable, strFilter, lPaseo), HttpStatus.OK);
    }




    
}

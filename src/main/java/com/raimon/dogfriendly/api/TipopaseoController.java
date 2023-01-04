package com.raimon.dogfriendly.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raimon.dogfriendly.entity.TipopaseoEntity;
import com.raimon.dogfriendly.service.TipopaseoService;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/tipopaseo")
public class TipopaseoController {
    @Autowired
    TipopaseoService oTipopaseoService;

    @GetMapping("/{id}")
    public ResponseEntity<TipopaseoEntity> get (@PathVariable(value="id") Long id){
        return new ResponseEntity<TipopaseoEntity>(oTipopaseoService.get(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Long> create(@RequestBody TipopaseoEntity oNewTipopaseoEntity) {
        return new ResponseEntity<Long>(oTipopaseoService.create(oNewTipopaseoEntity), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Long> update(@RequestBody TipopaseoEntity oTipopaseoEntity) {
        return new ResponseEntity<Long>(oTipopaseoService.update(oTipopaseoEntity), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<Long>(oTipopaseoService.delete(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oTipopaseoService.count(), HttpStatus.OK);
    }
















}

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

import com.raimon.dogfriendly.entity.PaseoEntity;
import com.raimon.dogfriendly.service.PaseoService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/paseo")
public class PaseoController {

    @Autowired
    PaseoService oPaseoService;

    @GetMapping("/{id}")
    public ResponseEntity<PaseoEntity> get (@PathVariable(value="id") Long id){
        return new ResponseEntity<PaseoEntity>(oPaseoService.get(id), HttpStatus.OK);
    }

    @PostMapping("/")
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
}

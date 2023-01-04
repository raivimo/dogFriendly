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

import com.raimon.dogfriendly.entity.PerroEntity;
import com.raimon.dogfriendly.service.PerroService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/perro")
public class PerroController {
    @Autowired
    PerroService oPerroService;

    @GetMapping("/{id}")
    public ResponseEntity<PerroEntity> get (@PathVariable(value="id") Long id){
        return new ResponseEntity<PerroEntity>(oPerroService.get(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Long> create(@RequestBody PerroEntity oNewPerroEntity) {
        return new ResponseEntity<Long>(oPerroService.create(oNewPerroEntity), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Long> update(@RequestBody PerroEntity oPerroEntity) {
        return new ResponseEntity<Long>(oPerroService.update(oPerroEntity), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<Long>(oPerroService.delete(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oPerroService.count(), HttpStatus.OK);
    }
}

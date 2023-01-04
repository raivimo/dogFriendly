package com.raimon.dogfriendly.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.raimon.dogfriendly.entity.TipousuarioEntity;
import com.raimon.dogfriendly.service.TipousuarioService;

@RestController
@RequestMapping("/tipousuario")

public class TipousuarioController {
 
    @Autowired
    TipousuarioService oTipousuarioService;
    
    @GetMapping("/{id}")
    public ResponseEntity<TipousuarioEntity> get (@PathVariable(value="id") Long id){
        return new ResponseEntity<TipousuarioEntity>(oTipousuarioService.get(id), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Long> update(@RequestBody TipousuarioEntity oTipousuarioEntity) {
        return new ResponseEntity<Long>(oTipousuarioService.update(oTipousuarioEntity), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oTipousuarioService.count(), HttpStatus.OK);
    }

    
    @GetMapping
    public ResponseEntity<Page<TipousuarioEntity>> getPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size) {
        return new ResponseEntity<>(oTipousuarioService.getPage(page, size), HttpStatus.OK);
    }
}

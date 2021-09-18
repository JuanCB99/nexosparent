/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexos.businessdomain.mercancias.controller;

import com.nexos.businessdomain.mercancias.business.transactions.BusinessTransaction;
import com.nexos.businessdomain.mercancias.entities.Mercancia;
import com.nexos.businessdomain.mercancias.entities.Usuarios;
import com.nexos.businessdomain.mercancias.exception.BusinessRuleException;
import com.nexos.businessdomain.mercancias.repository.IMercanciaRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author Juan
 */
@RestController
@RequestMapping("/mercancia")
public class MercanciaRestController {

    @Autowired
    IMercanciaRepository mercanciaRepository;

    @Autowired
    BusinessTransaction businessTransaction;

    @GetMapping()
    public ResponseEntity<List<Mercancia>> list() {

        return businessTransaction.list();
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mercancia> get(@PathVariable Integer id) {

        return mercanciaRepository.findById(id).
                map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());

    }

//    @PostMapping("/byUsuario")
//    public ResponseEntity<List<Mercancia>> findByUsuario(@RequestBody Usuarios usuarioId) {
//
//        List<Mercancia> mercancias = mercanciaRepository.findByUsuarioId(usuarioId);
//
//        return ResponseEntity.status(HttpStatus.FOUND).body(mercancias);
//    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<?> put(@PathVariable Integer idUsuario, @RequestBody Mercancia input) {

        return businessTransaction.put(idUsuario, input);
        
    }

    @PostMapping
    public ResponseEntity<Mercancia> post(@RequestBody Mercancia input) throws BusinessRuleException {

        return businessTransaction.save(input);
        
    }

    @DeleteMapping("/{idMercancia}/{idUsuario}")
    public ResponseEntity<?> delete(@PathVariable Integer idMercancia, @PathVariable Integer idUsuario) {

        return businessTransaction.delete(idMercancia, idUsuario);
        
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexos.businessdomain.usuario.controller;

import com.nexos.businessdomain.usuario.entities.Usuarios;
import com.nexos.businessdomain.usuario.repository.IUsuarioRepository;
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
@RequestMapping("/usuarios")
public class UsuarioRestController {
    
    @Autowired
    IUsuarioRepository usuariosRepository;
    
    @GetMapping()
    public List<Usuarios> list() {
        return usuariosRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Usuarios get(@PathVariable Integer id) {
        Usuarios usuarioRecuperado = usuariosRepository.findById(id).get();
        return usuarioRecuperado;
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Object input) {
        return null;
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Usuarios input) {
        Usuarios usuarioSave = usuariosRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSave);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }
    
}

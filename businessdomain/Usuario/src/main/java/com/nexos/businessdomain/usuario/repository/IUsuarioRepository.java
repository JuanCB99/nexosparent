/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexos.businessdomain.usuario.repository;

import com.nexos.businessdomain.usuario.entities.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Juan
 */
public interface IUsuarioRepository extends JpaRepository<Usuarios, Integer> {
    
}

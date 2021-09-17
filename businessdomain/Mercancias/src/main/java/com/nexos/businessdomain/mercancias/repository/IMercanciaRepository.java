/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexos.businessdomain.mercancias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nexos.businessdomain.mercancias.entities.Mercancia;
import com.nexos.businessdomain.mercancias.entities.Usuarios;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Juan
 */
public interface IMercanciaRepository extends JpaRepository<Mercancia, Integer> {
    
    public List<Mercancia> findByUsuarioId(Usuarios idUsuario);
    
    //@Query("SELECT m FROM Mercancia m WHERE m.nombreProducto = ?1")
    //public Mercancia findByNombreProducto(String mercanciaNombre);
    
}

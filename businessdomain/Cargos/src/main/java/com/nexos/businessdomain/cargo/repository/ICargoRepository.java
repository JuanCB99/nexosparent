/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexos.businessdomain.cargo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nexos.businessdomain.cargo.entities.Cargos;

/**
 *
 * @author Juan
 */
public interface ICargoRepository extends JpaRepository<Cargos, Integer> {
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexos.businessdomain.mercancias.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Juan
 */
@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "edad_usuario")
    private int edadUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_ingreso_usuario")
    @Temporal(TemporalType.DATE)
    private Date fechaIngresoUsuario;
    @JoinColumn(name = "cargo_id", referencedColumnName = "id_cargo")
    @ManyToOne(optional = false)
    private Cargos cargoId;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioId")
    private List<Mercancia> mercanciaList;
    
}

package com.efra.administraArchivos.entidades;

/**
 *
 * @author byefr
 */

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tUsuario")
@Getter
@Setter

public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "txt_usuario")
    private String txtUsuario;
    
    
    @Column(name = "txt_hash")
    private String txtHash;
    
    
    @ManyToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;
    
    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;
     
    @ManyToOne
    @JoinColumn(name = "id_area")
    private Area area;
    
    @Column(name = "activo")
    private Integer activo;
}

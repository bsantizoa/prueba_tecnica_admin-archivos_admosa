package com.efra.administraArchivos.entidades;

/**
 *
 * @author byefr
 */

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "tAccion")
@Getter
@Setter

public class Accion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_accion")
    private Integer idAccion;

    @Column(name = "txt_accion")
    private String txtAccion;
   
    @Column(name = "activo")
    private Integer activo;
}



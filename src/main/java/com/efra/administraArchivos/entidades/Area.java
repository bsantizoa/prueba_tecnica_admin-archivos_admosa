package com.efra.administraArchivos.entidades;

/**
 *
 * @author byefr
 */

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tArea")
@Getter
@Setter

public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_area")
    private Integer idArea;

    @Column(name = "txt_area")
    private String nombre;

    @Column(name = "activo")
    private Integer activo;
}
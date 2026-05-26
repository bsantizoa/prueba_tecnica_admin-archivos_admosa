
package com.efra.administraArchivos.entidades;

/**
 *
 * @author byefr
 */

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "tPersona")
@Getter
@Setter

public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Integer idPersona;

    @Column(name = "txt_nombre")
    private String txtNombre;
    
    
    @Column(name = "txt_apellido")
    private String txtApellido;
    
    
    @Column(name = "txt_correo")
    private String txtCorreo;
    
   
    @Column(name = "activo")
    private Integer activo;

}

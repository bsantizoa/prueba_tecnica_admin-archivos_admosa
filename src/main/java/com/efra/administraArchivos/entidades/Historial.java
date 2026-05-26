
package com.efra.administraArchivos.entidades;

/**
 *
 * @author byefr
 */

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tHistorial")
@Getter
@Setter

public class Historial {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private Integer idHistorial;
        
    @ManyToOne
    @JoinColumn(name = "id_archivo")
    private Archivos archivo;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "id_accion")
    private Accion accion;
    
    @Column(name = "txt_comentario")
    private String txtComentario;
    
    @Column(name = "activo")
    private Integer activo;    
}

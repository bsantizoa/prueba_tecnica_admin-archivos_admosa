
package com.efra.administraArchivos.entidades;

/**
 *
 * @author byefr
 */

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tArchivo")
@Getter
@Setter

public class Archivos {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_archivo")
    private Integer idArchivo;
    
    @Column(name = "txt_uuid_original")
    private String txtUuidOri;
    
    @Column(name = "txt_nom_original")
    private String txtNomOri;
    
    @Column(name = "txt_nom_fisico")
    private String txtNomFisico;
    
    @Column(name = "txt_extension")
    private String txtExtension;
    
    @Column(name = "txt_tipo_mime")
    private String txtTipoMime;
    
    @Column(name = "peso_byte")
    private Long pesoByte;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    
    @Column(name = "eliminado")
    private Boolean  eliminado;
}

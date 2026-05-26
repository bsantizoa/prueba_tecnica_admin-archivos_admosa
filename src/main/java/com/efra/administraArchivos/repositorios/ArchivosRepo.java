
package com.efra.administraArchivos.repositorios;

import java.util.Optional;

/**
 *
 * @author byefr
 */

import com.efra.administraArchivos.entidades.Archivos;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArchivosRepo extends JpaRepository<Archivos, Integer> {
    
    //Para buscar archivos por id
    Optional<Archivos> findById(Integer id);
    
    //Para mostrar los archivos eliminados y no eliminados
    List<Archivos> findByEliminadoFalse();
    
    //Para mostrar los archivos solo correspondiente al  area
    //List<Archivos> findByUsuarioIdUsuario(Integer idUsuario);
    List<Archivos> findByUsuario_IdUsuario(Integer idUsuario);
    
    //List<Archivos> findByUsuarioAreaIdArea(Integer idArea);
    List<Archivos> findByUsuario_Area_IdArea(Integer idArea);
    

}
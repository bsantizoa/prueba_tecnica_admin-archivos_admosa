package com.efra.administraArchivos.repositorios;

/**
 *
 * @author byefr
 */

import com.efra.administraArchivos.entidades.Historial;
import org.springframework.data.jpa.repository.JpaRepository;



public interface HistorialRepo extends JpaRepository<Historial, Integer> {
    
    

}



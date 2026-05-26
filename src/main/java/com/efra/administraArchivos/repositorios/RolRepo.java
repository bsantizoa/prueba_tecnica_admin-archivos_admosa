package com.efra.administraArchivos.repositorios;

/**
 *
 * @author byefr
 */

import com.efra.administraArchivos.entidades.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepo  extends JpaRepository<Rol, Integer>{
    
    
}

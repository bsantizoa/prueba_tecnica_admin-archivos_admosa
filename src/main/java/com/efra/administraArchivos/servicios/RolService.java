
package com.efra.administraArchivos.servicios;

/**
 *
 * @author byefr
 */

import com.efra.administraArchivos.entidades.Rol;
import com.efra.administraArchivos.repositorios.RolRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolService {
    
    @Autowired
    private RolRepo rolRepo;
    
    // Listar Roles
    public List<Rol> listaRoles() {
        return rolRepo.findAll();
    }
    
}

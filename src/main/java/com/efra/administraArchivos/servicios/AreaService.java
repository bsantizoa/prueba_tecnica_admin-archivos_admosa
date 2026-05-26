
package com.efra.administraArchivos.servicios;

/**
 *
 * @author byefr
 */


import com.efra.administraArchivos.entidades.Area;
import com.efra.administraArchivos.repositorios.AreaRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaService {
    
    @Autowired
    private AreaRepo areaRepo;
    
     // Listar Areas
    public List<Area> listaRoles() {
        return areaRepo.findAll();
    }
    
}

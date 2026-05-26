
package com.efra.administraArchivos.servicesImp;

/**
 *
 * @author byefr
 */
import com.efra.administraArchivos.entidades.Historial;
import com.efra.administraArchivos.repositorios.HistorialRepo;
import com.efra.administraArchivos.servicios.HistorialService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistorialServiceImp implements HistorialService{
    
    @Autowired
    private HistorialRepo historialRepo;
    
    @Override
    public Historial guardar(Historial historial){
        return historialRepo.save(historial);
    }
    
    @Override
    public List<Historial> listar(){
        return historialRepo.findAll();
    }
    
}

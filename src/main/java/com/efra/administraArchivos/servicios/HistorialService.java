package com.efra.administraArchivos.servicios;

/**
 *
 * @author byefr
 */

import com.efra.administraArchivos.entidades.Historial;
import java.util.List;

public interface HistorialService {
    
    Historial guardar(Historial historial);
    
    List<Historial> listar();
    
    
}

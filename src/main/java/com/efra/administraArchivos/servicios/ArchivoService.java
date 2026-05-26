package com.efra.administraArchivos.servicios;

/**
 *
 * @author byefr
 */

import com.efra.administraArchivos.entidades.Archivos;
import java.util.List;

public interface ArchivoService {
    
    List<Archivos> listar();
    
    //Para guardar los archivos
    Archivos guardar(Archivos archivo);
    
    //Para el regstro cuando se descargue y se busque un archivo por id
    Archivos buscarPorId(Integer id);
    
    //Para la eliminacion logica
    Archivos eliminar(Integer id);
    
    //Para buscar archivos eliminados y no eliminados
    List<Archivos> listarArchivos();
    
    //Mostrar archivos segun rol y area
    List<Archivos> listarPorUsuario(Integer idUsuario);

    List<Archivos> listarPorArea(Integer idArea);
    
    
}

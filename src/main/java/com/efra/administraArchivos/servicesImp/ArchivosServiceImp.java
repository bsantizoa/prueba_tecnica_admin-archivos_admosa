
package com.efra.administraArchivos.servicesImp;

/**
 *
 * @author byefr
 */

import com.efra.administraArchivos.entidades.Archivos;
import com.efra.administraArchivos.repositorios.ArchivosRepo;
import com.efra.administraArchivos.servicios.ArchivoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArchivosServiceImp implements ArchivoService{
    
    @Autowired
    private ArchivosRepo archivosRepo;
    

    @Override
    public List<Archivos> listar(){
        return archivosRepo.findAll();
    }
    
    @Override
    public Archivos guardar(Archivos archivo) {
        return archivosRepo.save(archivo);
    }
    
    @Override
    public Archivos buscarPorId(Integer id) {
        return archivosRepo.findById(id).orElse(null);
    }
    
    @Override
    public Archivos eliminar(Integer id){
        Archivos archivo = archivosRepo.findById(id).orElse(null);
        if(archivo != null){
            archivo.setEliminado(true);
            return archivosRepo.save(archivo);
        }
      return null;
    }
    
    @Override
    public List<Archivos> listarArchivos(){
        return archivosRepo.findByEliminadoFalse();
    }
    
    
    @Override
    public List<Archivos> listarPorUsuario(Integer idUsuario) {

        return archivosRepo.findByUsuario_IdUsuario(idUsuario);
        
    }

    @Override
    public List<Archivos> listarPorArea(Integer idArea) {

        return archivosRepo.findByUsuario_Area_IdArea(idArea);
    }
    
    
}

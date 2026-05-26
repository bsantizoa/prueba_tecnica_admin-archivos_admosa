package com.efra.administraArchivos.servicios;

/**
 *
 * @author byefr
 */

import com.efra.administraArchivos.entidades.Usuario;
import com.efra.administraArchivos.repositorios.UsuarioRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepo usuarioRepo;
    
    // Para listar todos los usuarios
    public List<Usuario> listaUsuarios(){
        return usuarioRepo.findAll();
    }
    
    //Guardar
    public Usuario guardarUsuario(Usuario usuario){
        return usuarioRepo.save(usuario);
    }
    
    //Busacar por id
    public Usuario buscarUsuId(Integer id){
        return usuarioRepo.findById(id).orElse(null);
    }
    
    //Eliminar
    public void eliminaUsu(Integer id){
        usuarioRepo.deleteById(id);
    }
    
    
    public Usuario login(String usuario, String password) {
        return usuarioRepo.findByTxtUsuarioAndTxtHash(usuario, password)
            .orElse(null);
    }
    
}

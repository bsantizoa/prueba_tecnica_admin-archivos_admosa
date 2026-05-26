package com.efra.administraArchivos.repositorios;

/**
 *
 * @author byefr
 */

import com.efra.administraArchivos.entidades.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepo extends JpaRepository<Usuario, Integer>{
    
    Optional<Usuario> findByTxtUsuarioAndTxtHash(String txtUsuario, String txtHash);
    
}

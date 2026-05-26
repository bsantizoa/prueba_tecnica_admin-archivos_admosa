package com.efra.administraArchivos.controllers;

/**
 *
 * @author byefr
 */
import com.efra.administraArchivos.entidades.Usuario;
import com.efra.administraArchivos.servicios.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.efra.administraArchivos.dto.Login;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:5173") // puerto para react
public class UsuarioController {
    
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listaUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario buscar(@PathVariable Integer id) {
        return usuarioService.buscarUsuId(id);
    }

    @PostMapping
    public Usuario guardar(@RequestBody Usuario usuario) {
        return usuarioService.guardarUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        usuarioService.eliminaUsu(id);
    }
    
    //para el login
    @PostMapping("/login")
    public Usuario login(@RequestBody Login request) {

        Usuario user = usuarioService.login(
            request.getUsuario(),
            request.getPassword()
        );

        if (user == null) {
            throw new RuntimeException("Credenciales inválidas");
        }

        return user;
    }
    
    
}

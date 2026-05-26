
package com.efra.administraArchivos.controllers;

/**
 *
 * @author byefr
 */

import com.efra.administraArchivos.entidades.Historial;
import com.efra.administraArchivos.servicios.HistorialService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/historial")
@CrossOrigin("http://localhost:5173")// para conectar con react
public class HistorialController {

    @Autowired
    private HistorialService historialService;

    @GetMapping
    public List<Historial> listarHistorial() {
        return historialService.listar();
    }
}
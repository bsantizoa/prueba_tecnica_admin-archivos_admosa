package com.efra.administraArchivos.controllers;

/**
 *
 * @author byefr
 */

import com.efra.administraArchivos.entidades.Archivos;
import com.efra.administraArchivos.entidades.Usuario;
import com.efra.administraArchivos.servicios.ArchivoService;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.efra.administraArchivos.entidades.Accion;
import com.efra.administraArchivos.entidades.Historial;
import com.efra.administraArchivos.servicios.HistorialService;
import com.efra.administraArchivos.servicios.UsuarioService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;



@RestController
@RequestMapping("/api/archivos")
@CrossOrigin("http://localhost:5173") // para conectar con reac

public class ArchivosController {
    
    @Autowired
    private ArchivoService archivoService;
    
    @Autowired
    private HistorialService historialService;
    
    @Value("${ruta.archivos}")
    private String rutaArchivos;
    
    //metodo para la carga de archivos
    @PostMapping("/subir")
    public Archivos subirArchivo(
            @RequestParam("archivo")MultipartFile archivo,
            @RequestParam("idUsuario") Integer idUsuario
    ) throws IOException{
        
        //genera el UUID para el archivo
        String uuid = UUID.randomUUID().toString();
        
        //nombre original del archivo
        String nombreOriginal = archivo.getOriginalFilename();
        
        //extencion del archivo
        String extension = "";
        
        if(nombreOriginal != null && nombreOriginal.contains(".")){
            extension = nombreOriginal.substring(nombreOriginal.lastIndexOf("."));
        }
        
        //nombre fisico del archivo
        String nombreFisico = uuid + extension;
        
        //crear ruta destino para el archivo
        File destino = new File(rutaArchivos + "/" + nombreFisico);
        
        //guardar archivo fisico
        archivo.transferTo(destino);
        
        //crear entidad
        Archivos nuevoArchivo = new Archivos();
        nuevoArchivo.setTxtUuidOri(uuid);
        nuevoArchivo.setTxtNomOri(nombreOriginal);
        nuevoArchivo.setTxtNomFisico(nombreFisico);
        nuevoArchivo.setTxtExtension(extension);
        nuevoArchivo.setTxtTipoMime(archivo.getContentType());
        nuevoArchivo.setPesoByte(archivo.getSize());
        nuevoArchivo.setEliminado(false);
        
        // usuario FK
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);
        
        nuevoArchivo.setUsuario(usuario);
        
        //se guarda en la db
        Archivos guardado = archivoService.guardar(nuevoArchivo);

    // registrar historial de los movimentos de los archivos
    Historial historial = new Historial();
    historial.setArchivo(guardado);
    historial.setUsuario(usuario);
    
    Accion accion = new Accion();
    accion.setIdAccion(1); // 1 = carga archivo
    
    historial.setAccion(accion);
    historial.setTxtComentario("Carga de archivo");
    historial.setActivo(1);
    historialService.guardar(historial);

    return guardado;
        
    }  
    
    //metodo para la descarga de archivos
    @GetMapping("/descargar/{id}")
    public ResponseEntity<Resource> descargarArchivo(@PathVariable Integer id) throws IOException{
        
        Archivos archivo = archivoService.buscarPorId(id);
        
        if(archivo == null){
            return ResponseEntity.notFound().build();
        }
        
        Path ruta = Paths.get(rutaArchivos).resolve(archivo.getTxtNomFisico());
        
        Resource recurso = new UrlResource(ruta.toUri());
        
        if(!recurso.exists()){
            return ResponseEntity.notFound().build();
        }
        
        //registar en el historial la descarga
        Historial historial = new Historial();
        historial.setArchivo(archivo);
        historial.setUsuario(archivo.getUsuario());
        
        Accion accion = new Accion();
        accion.setIdAccion(3);//Descarga de archivo
        
        historial.setAccion(accion);
        historial.setTxtComentario("Descarga de archivo");
        historial.setActivo(1);
        historialService.guardar(historial);
        
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, 
                        "attachment; filename=\"" + archivo.getTxtNomOri() + "\"")
                .body(recurso);
                
    }
    
    //Metodo para la visulizacion
    @GetMapping("/ver/{id}")
    public ResponseEntity<Resource> verArchivo(
        @PathVariable Integer id
    ) throws IOException{

        Archivos archivo = archivoService.buscarPorId(id);

        if(archivo == null){
            return ResponseEntity.notFound().build();
        }

        Path ruta = Paths.get(rutaArchivos).resolve(archivo.getTxtNomFisico());

        Resource recurso = new UrlResource(ruta.toUri());
        if(!recurso.exists()){
            return ResponseEntity.notFound().build();
        }

        // REGISTRAR HISTORIAL
        Historial historial = new Historial();
        historial.setArchivo(archivo);
        historial.setUsuario(archivo.getUsuario());
        Accion accion = new Accion();
        accion.setIdAccion(2); // VISUALIZAR
        historial.setAccion(accion);
        historial.setTxtComentario("Visualizacion de archivo");
        historial.setActivo(1);

        historialService.guardar(historial);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE,
                        archivo.getTxtTipoMime())
                .body(recurso);
    }
    
    
    
    
    //metodo para la eliminacion logica--------------------------------------------------------
    @DeleteMapping("/eliminar/{id}")
    public Archivos eliminarArchivo(@PathVariable Integer id){
    
        Archivos archivo = archivoService.eliminar(id);
        
        if(archivo != null){
            
            //registrar en historial la eliminacion
            Historial historial = new Historial();
            historial.setArchivo(archivo);
            historial.setUsuario(archivo.getUsuario());
            
            Accion accion = new Accion();
            accion.setIdAccion(4); //Eliminar
            
            historial.setAccion(accion);
            historial.setTxtComentario("Eliminacion logica de archivo");
            historial.setActivo(1);
            
            historialService.guardar(historial);
            
        }
        
        return archivo;
        
    }
    
    
    //metodo para mostrar archivos eliminados y no eliminados--------------
    @GetMapping
    public List<Archivos> listarArchivos(){
        return archivoService.listarArchivos();
    }
    
    
    @Autowired
    private UsuarioService usuarioService;
    
    

    @GetMapping("/usuario/{id}")
    public List<Archivos> listarArchivosUsuario(
            @PathVariable Integer id){

        //Usuario usuario = usuarioService.buscarPorId(id);
        Usuario usuario = usuarioService.buscarUsuId(id);

        // ADMIN
        if(usuario.getRol().getIdRol() == 4){

            return archivoService.listarArchivos();
        }

        // USUARIO ESTANDAR
        if(usuario.getRol().getIdRol() == 1){

            return archivoService.listarPorUsuario(id);
        }

        // JEFE AREA
        if(usuario.getRol().getIdRol() == 2){

            return archivoService.listarPorArea(
                    usuario.getArea().getIdArea()
            );
        }

        // GERENTE
        if(usuario.getRol().getIdRol() == 3){

            return archivoService.listarPorArea(
                    usuario.getArea().getIdArea()
            );
        }

        return List.of();
    }
    
    
}

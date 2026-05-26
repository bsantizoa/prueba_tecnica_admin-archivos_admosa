package com.efra.administraArchivos.repositorios;

/**
 *
 * @author byefr
 */
import com.efra.administraArchivos.entidades.Accion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccionRepo extends JpaRepository<Accion, Integer> {

}

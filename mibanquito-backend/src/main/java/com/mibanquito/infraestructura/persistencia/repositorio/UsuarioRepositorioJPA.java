package com.mibanquito.infraestructura.persistencia.repositorio;

import com.mibanquito.infraestructura.persistencia.entidad.UsuarioEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepositorioJPA extends JpaRepository<UsuarioEntidad, Long> {
    Optional<UsuarioEntidad> findByEmail(String email);
}

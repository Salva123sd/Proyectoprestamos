package com.mibanquito.infraestructura.persistencia.repositorio;

import com.mibanquito.infraestructura.persistencia.entidad.ClienteEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositorioJPA extends JpaRepository<ClienteEntidad, Long> { }

package com.mibanquito.infraestructura.persistencia.repositorio;

import com.mibanquito.infraestructura.persistencia.entidad.DocumentoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentoRepositorioJPA extends JpaRepository<DocumentoEntidad, Long> {

    List<DocumentoEntidad> findByPrestamo_Id(Long prestamoId);
}

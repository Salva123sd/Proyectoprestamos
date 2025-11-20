package com.mibanquito.infraestructura.persistencia.repositorio;

import com.mibanquito.infraestructura.persistencia.entidad.PrestamoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrestamoRepositorioJPA extends JpaRepository<PrestamoEntidad, Long> {

    List<PrestamoEntidad> findByCliente_Id(Long clienteId);
}

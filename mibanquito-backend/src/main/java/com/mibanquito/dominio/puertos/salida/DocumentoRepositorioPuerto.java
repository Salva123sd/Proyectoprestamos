package com.mibanquito.dominio.puertos.salida;

import com.mibanquito.dominio.modelo.DocumentoPrestamo;

import java.util.List;
import java.util.Optional;

public interface DocumentoRepositorioPuerto {

    DocumentoPrestamo guardar(DocumentoPrestamo doc);

    List<DocumentoPrestamo> listarPorPrestamo(Long prestamoId);

    Optional<DocumentoPrestamo> buscarPorId(Long id);
}

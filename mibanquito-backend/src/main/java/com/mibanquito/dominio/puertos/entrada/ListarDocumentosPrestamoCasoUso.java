package com.mibanquito.dominio.puertos.entrada;

import com.mibanquito.dominio.modelo.DocumentoPrestamo;

import java.util.List;

public interface ListarDocumentosPrestamoCasoUso {

    List<DocumentoPrestamo> listarPorPrestamo(Long prestamoId);
}

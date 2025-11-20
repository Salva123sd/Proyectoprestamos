package com.mibanquito.dominio.puertos.entrada;

import com.mibanquito.dominio.modelo.Prestamo;

public interface ObtenerPrestamoCasoUso {
    Prestamo obtenerPorId(Long id);
}

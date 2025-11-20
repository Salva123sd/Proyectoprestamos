package com.mibanquito.aplicacion;

import com.mibanquito.dominio.modelo.Prestamo;
import com.mibanquito.dominio.puertos.entrada.ObtenerPrestamoCasoUso;
import com.mibanquito.dominio.puertos.salida.PrestamoRepositorioPuerto;
import org.springframework.stereotype.Service;

@Service
public class ObtenerPrestamoServicio implements ObtenerPrestamoCasoUso {

    private final PrestamoRepositorioPuerto repositorio;

    public ObtenerPrestamoServicio(PrestamoRepositorioPuerto repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Prestamo obtenerPorId(Long id) {
        return repositorio.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));
    }
}

package com.mibanquito.dominio.puertos.salida;

import com.mibanquito.dominio.modelo.Prestamo;

import java.util.List;
import java.util.Optional;

public interface PrestamoRepositorioPuerto {

    Prestamo guardar(Prestamo prestamo);

    Optional<Prestamo> buscarPorId(Long id);

    List<Prestamo> listar();

    List<Prestamo> listarPorCliente(Long clienteId);
}

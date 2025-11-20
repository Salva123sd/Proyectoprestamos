package com.mibanquito.dominio.puertos.entrada;

import com.mibanquito.dominio.modelo.Prestamo;

import java.util.List;

public interface ListarPrestamosCasoUso {

    List<Prestamo> listar();

    List<Prestamo> listarPorCliente(Long clienteId);
}

package com.mibanquito.aplicacion;

import com.mibanquito.dominio.modelo.Prestamo;
import com.mibanquito.dominio.puertos.entrada.ListarPrestamosCasoUso;
import com.mibanquito.dominio.puertos.salida.PrestamoRepositorioPuerto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarPrestamosServicio implements ListarPrestamosCasoUso {

    private final PrestamoRepositorioPuerto repositorio;

    public ListarPrestamosServicio(PrestamoRepositorioPuerto repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Prestamo> listar() {
        return repositorio.listar();
    }

    @Override
    public List<Prestamo> listarPorCliente(Long clienteId) {
        return repositorio.listarPorCliente(clienteId);
    }
}

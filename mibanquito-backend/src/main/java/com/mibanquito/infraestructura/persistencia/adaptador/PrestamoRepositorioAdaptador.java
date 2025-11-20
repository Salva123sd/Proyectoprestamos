package com.mibanquito.infraestructura.persistencia.adaptador;

import com.mibanquito.dominio.modelo.Prestamo;
import com.mibanquito.dominio.puertos.salida.PrestamoRepositorioPuerto;
import com.mibanquito.infraestructura.persistencia.entidad.PrestamoEntidad;
import com.mibanquito.infraestructura.persistencia.repositorio.PrestamoRepositorioJPA;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PrestamoRepositorioAdaptador implements PrestamoRepositorioPuerto {

    private final PrestamoRepositorioJPA jpa;

    public PrestamoRepositorioAdaptador(PrestamoRepositorioJPA jpa) {
        this.jpa = jpa;
    }

    @Override
    public Prestamo guardar(Prestamo prestamo) {
        PrestamoEntidad entidad = PrestamoEntidad.desdeDominio(prestamo);
        return jpa.save(entidad).haciaDominio();
    }

    @Override
    public Optional<Prestamo> buscarPorId(Long id) {
        return jpa.findById(id).map(PrestamoEntidad::haciaDominio);
    }

    @Override
    public List<Prestamo> listar() {
        return jpa.findAll().stream()
                .map(PrestamoEntidad::haciaDominio)
                .toList();
    }

    @Override
    public List<Prestamo> listarPorCliente(Long clienteId) {
        return jpa.findByCliente_Id(clienteId).stream()
                .map(PrestamoEntidad::haciaDominio)
                .toList();
    }
}

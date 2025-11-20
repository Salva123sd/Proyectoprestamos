package com.mibanquito.aplicacion;

import com.mibanquito.dominio.modelo.Cliente;
import com.mibanquito.dominio.puertos.entrada.ObtenerClienteCasoUso;
import com.mibanquito.dominio.puertos.salida.ClienteRepositorioPuerto;
import org.springframework.stereotype.Service;

@Service
public class ObtenerClienteServicio implements ObtenerClienteCasoUso {

    private final ClienteRepositorioPuerto repositorio;

    public ObtenerClienteServicio(ClienteRepositorioPuerto repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Cliente obtenerPorId(Long id) {
        return repositorio.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }
}

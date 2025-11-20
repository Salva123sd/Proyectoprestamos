package com.mibanquito.aplicacion;

import com.mibanquito.dominio.modelo.Cliente;
import com.mibanquito.dominio.puertos.entrada.ListarClientesCasoUso;
import com.mibanquito.dominio.puertos.salida.ClienteRepositorioPuerto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ListarClientesServicio implements ListarClientesCasoUso {

    private final ClienteRepositorioPuerto repositorio;

    public ListarClientesServicio(ClienteRepositorioPuerto repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Cliente> listar() {
        return repositorio.listar();
    }
}

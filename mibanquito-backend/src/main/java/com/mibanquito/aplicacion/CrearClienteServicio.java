package com.mibanquito.aplicacion;

import com.mibanquito.dominio.modelo.Cliente;
import com.mibanquito.dominio.puertos.entrada.CrearClienteCasoUso;
import com.mibanquito.dominio.puertos.salida.ClienteRepositorioPuerto;
import org.springframework.stereotype.Service;

@Service
public class CrearClienteServicio implements CrearClienteCasoUso {

    private final ClienteRepositorioPuerto repositorio;

    public CrearClienteServicio(ClienteRepositorioPuerto repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Long crear(String nombre, String dni, String telefono, String direccion, String nota) {
        Cliente cliente = new Cliente(null, nombre, dni, telefono, direccion, nota);
        return repositorio.guardar(cliente).getId();
    }
}

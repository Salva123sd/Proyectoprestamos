package com.mibanquito.dominio.puertos.salida;

import com.mibanquito.dominio.modelo.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteRepositorioPuerto {

    Cliente guardar(Cliente cliente);

    List<Cliente> listar();

    Optional<Cliente> buscarPorId(Long id);
}

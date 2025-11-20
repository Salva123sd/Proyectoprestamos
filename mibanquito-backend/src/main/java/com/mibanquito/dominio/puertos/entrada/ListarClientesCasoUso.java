package com.mibanquito.dominio.puertos.entrada;

import com.mibanquito.dominio.modelo.Cliente;
import java.util.List;

public interface ListarClientesCasoUso {
    List<Cliente> listar();
}

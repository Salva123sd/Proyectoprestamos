package com.mibanquito.dominio.puertos.entrada;

import com.mibanquito.dominio.modelo.Cliente;

public interface ObtenerClienteCasoUso {
    Cliente obtenerPorId(Long id);
}

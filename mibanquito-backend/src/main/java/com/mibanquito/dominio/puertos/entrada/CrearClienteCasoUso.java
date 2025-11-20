package com.mibanquito.dominio.puertos.entrada;

public interface CrearClienteCasoUso {
    Long crear(String nombre, String dni, String telefono, String direccion, String nota);
}

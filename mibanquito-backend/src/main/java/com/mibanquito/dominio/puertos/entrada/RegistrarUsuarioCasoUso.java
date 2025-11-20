package com.mibanquito.dominio.puertos.entrada;

public interface RegistrarUsuarioCasoUso {
    Long registrar(String nombreCompleto, String negocio, String email, String passwordPlano);
}

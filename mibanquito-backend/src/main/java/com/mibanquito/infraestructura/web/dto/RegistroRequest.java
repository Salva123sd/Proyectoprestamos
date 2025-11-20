package com.mibanquito.infraestructura.web.dto;

public record RegistroRequest(
        String nombreCompleto,
        String negocio,
        String email,
        String password
) {}

package com.mibanquito.infraestructura.web.dto;

public record LoginResponse(
        Long id,
        String nombreCompleto,
        String negocio,
        String email,
        String rol
) {}

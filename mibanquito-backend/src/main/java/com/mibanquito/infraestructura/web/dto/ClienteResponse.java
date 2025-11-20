package com.mibanquito.infraestructura.web.dto;

public record ClienteResponse(
        Long id,
        String nombre,
        String dni,
        String telefono,
        String direccion,
        String nota
) {}

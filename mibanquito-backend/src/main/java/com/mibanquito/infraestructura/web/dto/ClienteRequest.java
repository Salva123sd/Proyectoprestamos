package com.mibanquito.infraestructura.web.dto;

public record ClienteRequest(
        String nombre,
        String dni,
        String telefono,
        String direccion,
        String nota
) {}

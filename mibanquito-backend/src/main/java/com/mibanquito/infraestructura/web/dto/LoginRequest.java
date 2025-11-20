package com.mibanquito.infraestructura.web.dto;

public record LoginRequest(
        String email,
        String password
) {}

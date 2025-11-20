package com.mibanquito.infraestructura.web.dto;

import java.time.LocalDateTime;

public record DocumentoResponse(
        Long id,
        String nombreArchivo,
        Long tamanioBytes,
        LocalDateTime fechaSubida
) {}

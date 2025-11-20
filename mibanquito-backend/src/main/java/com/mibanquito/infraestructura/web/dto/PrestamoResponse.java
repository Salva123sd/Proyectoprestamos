package com.mibanquito.infraestructura.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PrestamoResponse(
        Long id,
        Long clienteId,
        BigDecimal montoPrestado,
        BigDecimal interes,
        BigDecimal montoTotal,
        LocalDate fechaPrestamo,
        LocalDate fechaVencimiento,
        String estado,
        String tipoPrenda,
        String descripcionPrenda,
        BigDecimal valorEstimado
) {}

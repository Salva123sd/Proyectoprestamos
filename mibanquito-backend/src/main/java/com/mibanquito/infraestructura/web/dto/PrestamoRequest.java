package com.mibanquito.infraestructura.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PrestamoRequest(
        Long clienteId,
        BigDecimal montoPrestado,
        BigDecimal interes,
        LocalDate fechaPrestamo,
        LocalDate fechaVencimiento,
        String tipoPrenda,
        String descripcionPrenda,
        BigDecimal valorEstimado
) {}

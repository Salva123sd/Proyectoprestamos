package com.mibanquito.dominio.puertos.entrada;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface CrearPrestamoCasoUso {

    Long crear(
            Long clienteId,
            BigDecimal montoPrestado,
            BigDecimal interes,
            LocalDate fechaPrestamo,
            LocalDate fechaVencimiento,
            String tipoPrenda,
            String descripcionPrenda,
            BigDecimal valorEstimado
    );
}

package com.mibanquito.dominio.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empeño {

    private Long id;
    private Long clienteId;

    private String tipoPrenda;
    private String descripcion;
    private BigDecimal valorEstimado;
    private BigDecimal montoPrestado;

    private LocalDate fechaEmpeño;
    private LocalDate fechaVencimiento;

    private String estado;
}

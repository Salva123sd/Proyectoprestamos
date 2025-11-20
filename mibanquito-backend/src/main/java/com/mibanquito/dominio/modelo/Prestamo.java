package com.mibanquito.dominio.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Prestamo {

    private Long id;
    private Long clienteId;
    private BigDecimal montoPrestado;
    private BigDecimal interes;       // % opcional (puede ser null)
    private BigDecimal montoTotal;    // calculado
    private LocalDate fechaPrestamo;
    private LocalDate fechaVencimiento;
    private String estado;            // "PENDIENTE" o "PAGADO"

    // Datos de garantía / empeño (opcionales)
    private String tipoPrenda;
    private String descripcionPrenda;
    private BigDecimal valorEstimado;

    public Prestamo(Long id,
                    Long clienteId,
                    BigDecimal montoPrestado,
                    BigDecimal interes,
                    BigDecimal montoTotal,
                    LocalDate fechaPrestamo,
                    LocalDate fechaVencimiento,
                    String estado,
                    String tipoPrenda,
                    String descripcionPrenda,
                    BigDecimal valorEstimado) {
        this.id = id;
        this.clienteId = clienteId;
        this.montoPrestado = montoPrestado;
        this.interes = interes;
        this.montoTotal = montoTotal;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaVencimiento = fechaVencimiento;
        this.estado = estado;
        this.tipoPrenda = tipoPrenda;
        this.descripcionPrenda = descripcionPrenda;
        this.valorEstimado = valorEstimado;
    }

    public Long getId() { return id; }
    public Long getClienteId() { return clienteId; }
    public BigDecimal getMontoPrestado() { return montoPrestado; }
    public BigDecimal getInteres() { return interes; }
    public BigDecimal getMontoTotal() { return montoTotal; }
    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public String getEstado() { return estado; }
    public String getTipoPrenda() { return tipoPrenda; }
    public String getDescripcionPrenda() { return descripcionPrenda; }
    public BigDecimal getValorEstimado() { return valorEstimado; }
}

package com.mibanquito.aplicacion;

import com.mibanquito.dominio.modelo.Prestamo;
import com.mibanquito.dominio.puertos.entrada.CrearPrestamoCasoUso;
import com.mibanquito.dominio.puertos.salida.PrestamoRepositorioPuerto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class CrearPrestamoServicio implements CrearPrestamoCasoUso {

    private final PrestamoRepositorioPuerto repositorio;

    public CrearPrestamoServicio(PrestamoRepositorioPuerto repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Long crear(Long clienteId,
                      BigDecimal montoPrestado,
                      BigDecimal interes,
                      LocalDate fechaPrestamo,
                      LocalDate fechaVencimiento,
                      String tipoPrenda,
                      String descripcionPrenda,
                      BigDecimal valorEstimado) {

        // Si no hay interés, total = monto
        BigDecimal total = montoPrestado;
        if (interes != null) {
            BigDecimal factor = interes.divide(BigDecimal.valueOf(100));
            total = montoPrestado.add(montoPrestado.multiply(factor));
        }

        Prestamo prestamo = new Prestamo(
                null,
                clienteId,
                montoPrestado,
                interes,
                total,
                fechaPrestamo,
                fechaVencimiento,
                "PENDIENTE",
                tipoPrenda,
                descripcionPrenda,
                valorEstimado
        );

        return repositorio.guardar(prestamo).getId();
    }
}

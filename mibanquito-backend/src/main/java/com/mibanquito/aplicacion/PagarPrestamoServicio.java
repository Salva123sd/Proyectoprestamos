package com.mibanquito.aplicacion;

import com.mibanquito.dominio.modelo.Prestamo;
import com.mibanquito.dominio.puertos.entrada.PagarPrestamoCasoUso;
import com.mibanquito.dominio.puertos.salida.PrestamoRepositorioPuerto;
import org.springframework.stereotype.Service;

@Service
public class PagarPrestamoServicio implements PagarPrestamoCasoUso {

    private final PrestamoRepositorioPuerto repositorio;

    public PagarPrestamoServicio(PrestamoRepositorioPuerto repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public void marcarComoPagado(Long prestamoId) {
        Prestamo p = repositorio.buscarPorId(prestamoId)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));

        Prestamo pagado = new Prestamo(
                p.getId(),
                p.getClienteId(),
                p.getMontoPrestado(),
                p.getInteres(),
                p.getMontoTotal(),
                p.getFechaPrestamo(),
                p.getFechaVencimiento(),
                "PAGADO",
                p.getTipoPrenda(),
                p.getDescripcionPrenda(),
                p.getValorEstimado()
        );

        repositorio.guardar(pagado);
    }
}

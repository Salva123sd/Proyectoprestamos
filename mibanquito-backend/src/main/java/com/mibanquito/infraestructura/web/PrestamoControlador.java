package com.mibanquito.infraestructura.web;

import com.mibanquito.dominio.modelo.Prestamo;
import com.mibanquito.dominio.puertos.entrada.CrearPrestamoCasoUso;
import com.mibanquito.dominio.puertos.entrada.ListarPrestamosCasoUso;
import com.mibanquito.dominio.puertos.entrada.ObtenerPrestamoCasoUso;
import com.mibanquito.dominio.puertos.entrada.PagarPrestamoCasoUso;
import com.mibanquito.infraestructura.web.dto.PrestamoRequest;
import com.mibanquito.infraestructura.web.dto.PrestamoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class PrestamoControlador {

    private final CrearPrestamoCasoUso crear;
    private final ListarPrestamosCasoUso listar;
    private final ObtenerPrestamoCasoUso obtener;
    private final PagarPrestamoCasoUso pagar;

    public PrestamoControlador(CrearPrestamoCasoUso crear,
                               ListarPrestamosCasoUso listar,
                               ObtenerPrestamoCasoUso obtener,
                               PagarPrestamoCasoUso pagar) {
        this.crear = crear;
        this.listar = listar;
        this.obtener = obtener;
        this.pagar = pagar;
    }

    @PostMapping
    public ResponseEntity<Long> crearPrestamo(@RequestBody PrestamoRequest req) {
        Long id = crear.crear(
                req.clienteId(),
                req.montoPrestado(),
                req.interes(),
                req.fechaPrestamo(),
                req.fechaVencimiento(),
                req.tipoPrenda(),
                req.descripcionPrenda(),
                req.valorEstimado()
        );
        return ResponseEntity.ok(id);
    }

    @GetMapping
    public ResponseEntity<List<PrestamoResponse>> listarPrestamos(
            @RequestParam(value = "clienteId", required = false) Long clienteId
    ) {
        List<Prestamo> prestamos = (clienteId == null)
                ? listar.listar()
                : listar.listarPorCliente(clienteId);

        List<PrestamoResponse> response = prestamos.stream()
                .map(p -> new PrestamoResponse(
                        p.getId(),
                        p.getClienteId(),
                        p.getMontoPrestado(),
                        p.getInteres(),
                        p.getMontoTotal(),
                        p.getFechaPrestamo(),
                        p.getFechaVencimiento(),
                        p.getEstado(),
                        p.getTipoPrenda(),
                        p.getDescripcionPrenda(),
                        p.getValorEstimado()
                ))
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestamoResponse> obtenerPrestamo(@PathVariable Long id) {
        Prestamo p = obtener.obtenerPorId(id);
        return ResponseEntity.ok(new PrestamoResponse(
                p.getId(),
                p.getClienteId(),
                p.getMontoPrestado(),
                p.getInteres(),
                p.getMontoTotal(),
                p.getFechaPrestamo(),
                p.getFechaVencimiento(),
                p.getEstado(),
                p.getTipoPrenda(),
                p.getDescripcionPrenda(),
                p.getValorEstimado()
        ));
    }

    @PutMapping("/{id}/pagar")
    public ResponseEntity<Void> marcarComoPagado(@PathVariable Long id) {
        pagar.marcarComoPagado(id);
        return ResponseEntity.noContent().build();
    }
}

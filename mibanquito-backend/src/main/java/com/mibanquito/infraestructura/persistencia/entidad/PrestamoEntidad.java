package com.mibanquito.infraestructura.persistencia.entidad;

import com.mibanquito.dominio.modelo.Prestamo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "prestamos")
@Data               // 🔥 Genera getters, setters, equals, hashCode, toString
@NoArgsConstructor  // 🔥 Constructor vacío
@AllArgsConstructor // 🔥 Constructor con todos los parámetros
public class PrestamoEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con cliente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntidad cliente;

    private BigDecimal montoPrestado;
    private BigDecimal interes;
    private BigDecimal montoTotal;
    private LocalDate fechaPrestamo;
    private LocalDate fechaVencimiento;
    private String estado;

    private String tipoPrenda;
    private String descripcionPrenda;
    private BigDecimal valorEstimado;

    // 🔁 Dominio → Entidad
    public static PrestamoEntidad desdeDominio(Prestamo p) {
        ClienteEntidad refCliente = new ClienteEntidad();
        refCliente.setId(p.getClienteId()); // ClienteEntidad tiene @Data, ya tiene setId()

        return new PrestamoEntidad(
                p.getId(),
                refCliente,
                p.getMontoPrestado(),
                p.getInteres(),
                p.getMontoTotal(),
                p.getFechaPrestamo(),
                p.getFechaVencimiento(),
                p.getEstado(),
                p.getTipoPrenda(),
                p.getDescripcionPrenda(),
                p.getValorEstimado()
        );
    }

    // 🔁 Entidad → Dominio
    public Prestamo haciaDominio() {
        Long clienteId = (cliente != null) ? cliente.getId() : null;

        return new Prestamo(
                id,
                clienteId,
                montoPrestado,
                interes,
                montoTotal,
                fechaPrestamo,
                fechaVencimiento,
                estado,
                tipoPrenda,
                descripcionPrenda,
                valorEstimado
        );
    }
}

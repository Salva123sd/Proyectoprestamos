package com.mibanquito.infraestructura.persistencia.entidad;

import com.mibanquito.dominio.modelo.Empeño;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "empenos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpeñoEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con cliente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntidad cliente;

    private String tipoPrenda;
    private String descripcion;
    private BigDecimal valorEstimado;
    private BigDecimal montoPrestado;

    private LocalDate fechaEmpeño;
    private LocalDate fechaVencimiento;

    private String estado; // activo, retirado, vencido

    // 🔁 Dominio → Entidad
    public static EmpeñoEntidad desdeDominio(Empeño e) {
        ClienteEntidad refCliente = new ClienteEntidad();
        refCliente.setId(e.getClienteId());

        return new EmpeñoEntidad(
                e.getId(),
                refCliente,
                e.getTipoPrenda(),
                e.getDescripcion(),
                e.getValorEstimado(),
                e.getMontoPrestado(),
                e.getFechaEmpeño(),
                e.getFechaVencimiento(),
                e.getEstado()
        );
    }

    // 🔁 Entidad → Dominio
    public Empeño haciaDominio() {
        Long clienteId = (cliente != null) ? cliente.getId() : null;

        return new Empeño(
                id,
                clienteId,
                tipoPrenda,
                descripcion,
                valorEstimado,
                montoPrestado,
                fechaEmpeño,
                fechaVencimiento,
                estado
        );
    }
}

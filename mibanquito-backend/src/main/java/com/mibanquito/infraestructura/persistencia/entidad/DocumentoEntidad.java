package com.mibanquito.infraestructura.persistencia.entidad;

import com.mibanquito.dominio.modelo.DocumentoPrestamo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "documentos_prestamo")
@Data               // 🔥 Genera getters, setters, equals, hashCode y toString
@NoArgsConstructor  // 🔥 Constructor vacío
@AllArgsConstructor // 🔥 Constructor con todos los argumentos
public class DocumentoEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prestamo_id", nullable = false)
    private PrestamoEntidad prestamo;

    private String nombreArchivo;
    private String rutaArchivo;
    private Long tamanioBytes;
    private LocalDateTime fechaSubida;

    // 🔁 Dominio → Entidad
    public static DocumentoEntidad desdeDominio(DocumentoPrestamo d) {
        PrestamoEntidad ref = new PrestamoEntidad();
        ref.setId(d.getPrestamoId());  // debe existir setId() en PrestamoEntidad

        return new DocumentoEntidad(
                d.getId(),
                ref,
                d.getNombreArchivo(),
                d.getRutaArchivo(),
                d.getTamanioBytes(),
                d.getFechaSubida()
        );
    }

    // 🔁 Entidad → Dominio
    public DocumentoPrestamo haciaDominio() {
        Long prestamoId = (prestamo != null) ? prestamo.getId() : null;

        return new DocumentoPrestamo(
                id,
                prestamoId,
                nombreArchivo,
                rutaArchivo,
                tamanioBytes,
                fechaSubida
        );
    }
}

package com.mibanquito.dominio.modelo;

import java.time.LocalDateTime;

public class DocumentoPrestamo {

    private Long id;
    private Long prestamoId;
    private String nombreArchivo;
    private String rutaArchivo;   // ruta relativa dentro de /uploads
    private Long tamanioBytes;
    private LocalDateTime fechaSubida;

    public DocumentoPrestamo(Long id,
                             Long prestamoId,
                             String nombreArchivo,
                             String rutaArchivo,
                             Long tamanioBytes,
                             LocalDateTime fechaSubida) {
        this.id = id;
        this.prestamoId = prestamoId;
        this.nombreArchivo = nombreArchivo;
        this.rutaArchivo = rutaArchivo;
        this.tamanioBytes = tamanioBytes;
        this.fechaSubida = fechaSubida;
    }

    public Long getId() { return id; }
    public Long getPrestamoId() { return prestamoId; }
    public String getNombreArchivo() { return nombreArchivo; }
    public String getRutaArchivo() { return rutaArchivo; }
    public Long getTamanioBytes() { return tamanioBytes; }
    public LocalDateTime getFechaSubida() { return fechaSubida; }
}

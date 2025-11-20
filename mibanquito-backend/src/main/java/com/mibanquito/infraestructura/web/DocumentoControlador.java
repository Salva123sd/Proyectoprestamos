package com.mibanquito.infraestructura.web;

import com.mibanquito.dominio.modelo.DocumentoPrestamo;
import com.mibanquito.dominio.puertos.entrada.ListarDocumentosPrestamoCasoUso;
import com.mibanquito.dominio.puertos.entrada.SubirDocumentoCasoUso;
import com.mibanquito.dominio.puertos.salida.ArchivoAlmacenamientoPuerto;
import com.mibanquito.dominio.puertos.salida.DocumentoRepositorioPuerto;
import com.mibanquito.infraestructura.web.dto.DocumentoResponse;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@RestController
public class DocumentoControlador {

    private final SubirDocumentoCasoUso subirDocumentoCasoUso;
    private final ListarDocumentosPrestamoCasoUso listarDocumentosCasoUso;
    private final DocumentoRepositorioPuerto documentoRepositorio;
    private final ArchivoAlmacenamientoPuerto almacenamiento;

    public DocumentoControlador(SubirDocumentoCasoUso subirDocumentoCasoUso,
                                ListarDocumentosPrestamoCasoUso listarDocumentosCasoUso,
                                DocumentoRepositorioPuerto documentoRepositorio,
                                ArchivoAlmacenamientoPuerto almacenamiento) {
        this.subirDocumentoCasoUso = subirDocumentoCasoUso;
        this.listarDocumentosCasoUso = listarDocumentosCasoUso;
        this.documentoRepositorio = documentoRepositorio;
        this.almacenamiento = almacenamiento;
    }

    @PostMapping("/prestamos/{prestamoId}/documentos")
    public ResponseEntity<Long> subirDocumento(
            @PathVariable Long prestamoId,
            @RequestParam("file") MultipartFile file) throws Exception {

        try (InputStream datos = file.getInputStream()) {
            Long id = subirDocumentoCasoUso.subir(
                    prestamoId,
                    file.getOriginalFilename(),
                    file.getSize(),
                    datos
            );
            return ResponseEntity.ok(id);
        }
    }

    @GetMapping("/prestamos/{prestamoId}/documentos")
    public ResponseEntity<List<DocumentoResponse>> listarDocumentos(@PathVariable Long prestamoId) {
        List<DocumentoResponse> docs = listarDocumentosCasoUso.listarPorPrestamo(prestamoId)
                .stream()
                .map(d -> new DocumentoResponse(
                        d.getId(),
                        d.getNombreArchivo(),
                        d.getTamanioBytes(),
                        d.getFechaSubida()
                ))
                .toList();

        return ResponseEntity.ok(docs);
    }

    @GetMapping("/documentos/{id}/download")
    public ResponseEntity<InputStreamResource> descargar(@PathVariable Long id) {
        DocumentoPrestamo doc = documentoRepositorio.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Documento no encontrado"));

        InputStream is = almacenamiento.cargarArchivo(doc.getRutaArchivo());
        InputStreamResource resource = new InputStreamResource(is);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + doc.getNombreArchivo() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}

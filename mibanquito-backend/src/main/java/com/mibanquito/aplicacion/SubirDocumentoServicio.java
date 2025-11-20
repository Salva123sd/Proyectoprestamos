package com.mibanquito.aplicacion;

import com.mibanquito.dominio.modelo.DocumentoPrestamo;
import com.mibanquito.dominio.puertos.entrada.SubirDocumentoCasoUso;
import com.mibanquito.dominio.puertos.salida.ArchivoAlmacenamientoPuerto;
import com.mibanquito.dominio.puertos.salida.DocumentoRepositorioPuerto;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDateTime;

@Service
public class SubirDocumentoServicio implements SubirDocumentoCasoUso {

    private final ArchivoAlmacenamientoPuerto almacenamiento;
    private final DocumentoRepositorioPuerto repositorio;

    public SubirDocumentoServicio(ArchivoAlmacenamientoPuerto almacenamiento,
                                  DocumentoRepositorioPuerto repositorio) {
        this.almacenamiento = almacenamiento;
        this.repositorio = repositorio;
    }

    @Override
    public Long subir(Long prestamoId, String nombreOriginal, long tamanioBytes, InputStream datos) {
        // 1) Guarda archivo físico y obtiene ruta relativa
        String ruta = almacenamiento.guardarArchivo(prestamoId, nombreOriginal, datos);

        // 2) Guarda metadatos en BD
        DocumentoPrestamo doc = new DocumentoPrestamo(
                null,
                prestamoId,
                nombreOriginal,
                ruta,
                tamanioBytes,
                LocalDateTime.now()
        );

        return repositorio.guardar(doc).getId();
    }
}

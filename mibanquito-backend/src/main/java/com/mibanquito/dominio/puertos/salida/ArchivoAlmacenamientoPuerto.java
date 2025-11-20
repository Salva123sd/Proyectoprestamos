package com.mibanquito.dominio.puertos.salida;

import java.io.InputStream;

public interface ArchivoAlmacenamientoPuerto {

    /**
     * Guarda un archivo físico y devuelve la ruta relativa donde se almacenó.
     */
    String guardarArchivo(Long prestamoId, String nombreOriginal, InputStream datos);

    /**
     * Devuelve un InputStream para leer el archivo almacenado.
     */
    InputStream cargarArchivo(String rutaRelativa);
}

package com.mibanquito.dominio.puertos.entrada;

import java.io.InputStream;

public interface SubirDocumentoCasoUso {

    Long subir(Long prestamoId,
               String nombreOriginal,
               long tamanioBytes,
               InputStream datos);
}

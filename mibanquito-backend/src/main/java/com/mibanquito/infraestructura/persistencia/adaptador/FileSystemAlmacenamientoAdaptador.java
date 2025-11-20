package com.mibanquito.infraestructura.persistencia.adaptador;

import com.mibanquito.dominio.puertos.salida.ArchivoAlmacenamientoPuerto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.time.Instant;

@Component
public class FileSystemAlmacenamientoAdaptador implements ArchivoAlmacenamientoPuerto {

    private final Path raiz;

    public FileSystemAlmacenamientoAdaptador(
            @Value("${mibanquito.uploads-dir:uploads}") String uploadsDir) {
        this.raiz = Paths.get(uploadsDir).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.raiz);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear directorio de uploads", e);
        }
    }

    @Override
    public String guardarArchivo(Long prestamoId, String nombreOriginal, InputStream datos) {
        try {
            Path dirPrestamo = raiz.resolve("prestamos").resolve(prestamoId.toString());
            Files.createDirectories(dirPrestamo);

            String limpio = nombreOriginal.replaceAll("[^a-zA-Z0-9\\.\\-_]", "_");
            String nuevoNombre = Instant.now().toEpochMilli() + "_" + limpio;

            Path destino = dirPrestamo.resolve(nuevoNombre);
            Files.copy(datos, destino, StandardCopyOption.REPLACE_EXISTING);

            // ruta relativa desde raiz
            return raiz.relativize(destino).toString().replace("\\", "/");
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar archivo", e);
        }
    }

    @Override
    public InputStream cargarArchivo(String rutaRelativa) {
        try {
            Path archivo = raiz.resolve(rutaRelativa).normalize();
            return Files.newInputStream(archivo, StandardOpenOption.READ);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo leer el archivo: " + rutaRelativa, e);
        }
    }
}

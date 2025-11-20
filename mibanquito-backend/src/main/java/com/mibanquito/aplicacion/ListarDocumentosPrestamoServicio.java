package com.mibanquito.aplicacion;

import com.mibanquito.dominio.modelo.DocumentoPrestamo;
import com.mibanquito.dominio.puertos.entrada.ListarDocumentosPrestamoCasoUso;
import com.mibanquito.dominio.puertos.salida.DocumentoRepositorioPuerto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarDocumentosPrestamoServicio implements ListarDocumentosPrestamoCasoUso {

    private final DocumentoRepositorioPuerto repositorio;

    public ListarDocumentosPrestamoServicio(DocumentoRepositorioPuerto repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<DocumentoPrestamo> listarPorPrestamo(Long prestamoId) {
        return repositorio.listarPorPrestamo(prestamoId);
    }
}

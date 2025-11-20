package com.mibanquito.infraestructura.persistencia.adaptador;

import com.mibanquito.dominio.modelo.DocumentoPrestamo;
import com.mibanquito.dominio.puertos.salida.DocumentoRepositorioPuerto;
import com.mibanquito.infraestructura.persistencia.entidad.DocumentoEntidad;
import com.mibanquito.infraestructura.persistencia.repositorio.DocumentoRepositorioJPA;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DocumentoRepositorioAdaptador implements DocumentoRepositorioPuerto {

    private final DocumentoRepositorioJPA jpa;

    public DocumentoRepositorioAdaptador(DocumentoRepositorioJPA jpa) {
        this.jpa = jpa;
    }

    @Override
    public DocumentoPrestamo guardar(DocumentoPrestamo doc) {
        DocumentoEntidad entidad = DocumentoEntidad.desdeDominio(doc);
        return jpa.save(entidad).haciaDominio();
    }

    @Override
    public List<DocumentoPrestamo> listarPorPrestamo(Long prestamoId) {
        return jpa.findByPrestamo_Id(prestamoId).stream()
                .map(DocumentoEntidad::haciaDominio)
                .toList();
    }

    @Override
    public Optional<DocumentoPrestamo> buscarPorId(Long id) {
        return jpa.findById(id).map(DocumentoEntidad::haciaDominio);
    }
}

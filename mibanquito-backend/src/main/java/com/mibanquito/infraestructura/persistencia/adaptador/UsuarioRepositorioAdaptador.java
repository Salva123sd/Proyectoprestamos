package com.mibanquito.infraestructura.persistencia.adaptador;
import com.mibanquito.dominio.modelo.Usuario;
import com.mibanquito.dominio.puertos.salida.UsuarioRepositorioPuerto;
import com.mibanquito.infraestructura.persistencia.entidad.UsuarioEntidad;
import com.mibanquito.infraestructura.persistencia.repositorio.UsuarioRepositorioJPA;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Adaptador que implementa el Puerto de Salida para la gestión de Usuarios.
 * Realiza la conversión de objetos de Dominio (Usuario) a Entidades de Persistencia (UsuarioEntidad) y viceversa.
 */
@Component
public class UsuarioRepositorioAdaptador implements UsuarioRepositorioPuerto {

    private final UsuarioRepositorioJPA jpa;

    // Inyección de la interfaz JPA (Implementación de la infraestructura)
    public UsuarioRepositorioAdaptador(UsuarioRepositorioJPA jpa) {
        this.jpa = jpa;
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        // 1. Convertir el objeto de Dominio a la Entidad JPA
        UsuarioEntidad entidad = UsuarioEntidad.desdeDominio(usuario);
        
        // 2. Guardar la Entidad y obtener la Entidad guardada
        UsuarioEntidad entidadGuardada = jpa.save(entidad);
        
        // 3. Convertir la Entidad guardada de vuelta al objeto de Dominio y retornar
        // CORRECCIÓN: Se usa variable intermedia para el método de instancia haciaDominio()
        return entidadGuardada.haciaDominio();
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        // CORRECCIÓN: Se usa lambda (entidad -> entidad.haciaDominio()) en lugar de referencia a método (::)
        // para mapear Optional<Entidad> a Optional<Dominio>
        return jpa.findByEmail(email).map(entidad -> entidad.haciaDominio());
    }
}
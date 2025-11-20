package com.mibanquito.infraestructura.persistencia.adaptador;

import com.mibanquito.dominio.modelo.Cliente;
import com.mibanquito.dominio.puertos.salida.ClienteRepositorioPuerto;
import com.mibanquito.infraestructura.persistencia.entidad.ClienteEntidad;
import com.mibanquito.infraestructura.persistencia.repositorio.ClienteRepositorioJPA;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adaptador que implementa el Puerto de Salida del Dominio
 * y utiliza la tecnología JPA (Spring Data) para la persistencia.
 */
@Component
public class ClienteRepositorioAdaptador implements ClienteRepositorioPuerto {

    private final ClienteRepositorioJPA jpa;

    // Inyección de la interfaz JPA (Implementación de la infraestructura)
    public ClienteRepositorioAdaptador(ClienteRepositorioJPA jpa) {
        this.jpa = jpa;
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        // 1. Convertir el objeto de Dominio a la Entidad JPA
        ClienteEntidad entidad = ClienteEntidad.desdeDominio(cliente);
        
        // 2. Guardar la Entidad y obtener la Entidad guardada
        ClienteEntidad entidadGuardada = jpa.save(entidad);
        
        // 3. Convertir la Entidad guardada de vuelta al objeto de Dominio y retornar
        return entidadGuardada.haciaDominio();
    }

    @Override
    public List<Cliente> listar() {
        // Usa lambda para mapear la Entidad JPA (Entidad) al objeto de Dominio (Cliente)
        return jpa.findAll().stream()
                .map(entidad -> entidad.haciaDominio())
                .toList();
    }

    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        // Usa map para transformar Optional<ClienteEntidad> a Optional<Cliente>
        return jpa.findById(id)
            .map(entidad -> entidad.haciaDominio());
    }
}
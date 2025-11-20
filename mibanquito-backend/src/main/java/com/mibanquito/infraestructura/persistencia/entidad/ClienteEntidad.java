package com.mibanquito.infraestructura.persistencia.entidad;

import com.mibanquito.dominio.modelo.Cliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data               // 🔥 Genera getters, setters, toString, equals, hashCode
@NoArgsConstructor  // 🔥 Constructor vacío
@AllArgsConstructor // 🔥 Constructor con todos los parámetros
public class ClienteEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String dni;
    private String telefono;
    private String direccion;
    private String nota;

    // Conversión Dominio → Entidad
    public static ClienteEntidad desdeDominio(Cliente c) {
        return new ClienteEntidad(
                c.getId(),
                c.getNombre(),
                c.getDni(),
                c.getTelefono(),
                c.getDireccion(),
                c.getNota()
        );
    }

    // Conversión Entidad → Dominio
    public Cliente haciaDominio() {
        return new Cliente(
                id,
                nombre,
                dni,
                telefono,
                direccion,
                nota
        );
    }
}

package com.mibanquito.infraestructura.persistencia.entidad;

import com.mibanquito.dominio.modelo.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data               // 🔥 Genera getters, setters, equals, hashCode, toString
@NoArgsConstructor  // 🔥 Constructor vacío
@AllArgsConstructor // 🔥 Constructor con todos los argumentos
public class UsuarioEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCompleto;
    private String negocio;

    @Column(unique = true)
    private String email;

    private String password;
    private String rol;

    // Conversión desde dominio → entidad
    public static UsuarioEntidad desdeDominio(Usuario u) {
        return new UsuarioEntidad(
                u.getId(),
                u.getNombreCompleto(),
                u.getNegocio(),
                u.getEmail(),
                u.getPassword(),
                u.getRol()
        );
    }

    // Conversión entidad → dominio
    public Usuario haciaDominio() {
        return new Usuario(
                id,
                nombreCompleto,
                negocio,
                email,
                password,
                rol
        );
    }
}

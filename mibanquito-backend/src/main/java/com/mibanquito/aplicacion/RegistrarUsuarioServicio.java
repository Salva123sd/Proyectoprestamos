package com.mibanquito.aplicacion;

import com.mibanquito.dominio.modelo.Usuario;
import com.mibanquito.dominio.puertos.entrada.RegistrarUsuarioCasoUso;
import com.mibanquito.dominio.puertos.salida.UsuarioRepositorioPuerto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrarUsuarioServicio implements RegistrarUsuarioCasoUso {

    private final UsuarioRepositorioPuerto repositorio;
    private final PasswordEncoder passwordEncoder;

    // 👉 ahora se inyecta PasswordEncoder
    public RegistrarUsuarioServicio(UsuarioRepositorioPuerto repositorio, PasswordEncoder passwordEncoder) {
        this.repositorio = repositorio;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Long registrar(String nombreCompleto, String negocio, String email, String passwordPlano) {
        if (repositorio.buscarPorEmail(email).isPresent()) {
            throw new RuntimeException("El email ya está registrado");
        }

        // 👉 encriptamos la contraseña ANTES de guardar
        String passwordEncriptado = passwordEncoder.encode(passwordPlano);

        Usuario usuario = new Usuario(
                null,
                nombreCompleto,
                negocio,
                email,
                passwordEncriptado,   // 👈 se guarda cifrada
                "USUARIO"
        );

        Usuario guardado = repositorio.guardar(usuario);
        return guardado.getId();
    }
}

package com.mibanquito.aplicacion;

import com.mibanquito.dominio.modelo.Usuario;
import com.mibanquito.dominio.puertos.entrada.RegistrarUsuarioCasoUso;
import com.mibanquito.dominio.puertos.salida.UsuarioRepositorioPuerto;
import org.springframework.stereotype.Service;

@Service
public class RegistrarUsuarioServicio implements RegistrarUsuarioCasoUso {

    private final UsuarioRepositorioPuerto repositorio;

    public RegistrarUsuarioServicio(UsuarioRepositorioPuerto repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Long registrar(String nombreCompleto, String negocio, String email, String passwordPlano) {
        Usuario usuario = new Usuario(
                null,
                nombreCompleto,
                negocio,
                email,
                passwordPlano,
                "USUARIO"
        );

        Usuario guardado = repositorio.guardar(usuario);
        return guardado.getId();
    }
}

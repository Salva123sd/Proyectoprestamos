package com.mibanquito.dominio.puertos.salida;

import com.mibanquito.dominio.modelo.Usuario;
import java.util.Optional;

public interface UsuarioRepositorioPuerto {
    Optional<Usuario> buscarPorEmail(String email);
    Usuario guardar(Usuario usuario);
}

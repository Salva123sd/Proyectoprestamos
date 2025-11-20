package com.mibanquito.aplicacion;

import com.mibanquito.dominio.modelo.Usuario;
import com.mibanquito.dominio.puertos.entrada.LoginCasoUso;
import com.mibanquito.dominio.puertos.salida.UsuarioRepositorioPuerto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServicio implements LoginCasoUso {

    private final UsuarioRepositorioPuerto repositorio;
    private final PasswordEncoder passwordEncoder;

    public LoginServicio(UsuarioRepositorioPuerto repositorio, PasswordEncoder passwordEncoder) {
        this.repositorio = repositorio;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario login(String email, String passwordPlano) {
        return repositorio.buscarPorEmail(email)
                .filter(u -> passwordEncoder.matches(passwordPlano, u.getPassword()))
                .orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));
    }
}

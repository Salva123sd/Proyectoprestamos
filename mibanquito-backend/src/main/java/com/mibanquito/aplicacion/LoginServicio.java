package com.mibanquito.aplicacion;

import com.mibanquito.dominio.modelo.Usuario;
import com.mibanquito.dominio.puertos.entrada.LoginCasoUso;
import com.mibanquito.dominio.puertos.salida.UsuarioRepositorioPuerto;
import org.springframework.stereotype.Service;

@Service
public class LoginServicio implements LoginCasoUso {

    private final UsuarioRepositorioPuerto repositorio;

    public LoginServicio(UsuarioRepositorioPuerto repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Usuario login(String email, String passwordPlano) {
        return repositorio.buscarPorEmail(email)
                .filter(u -> u.getPassword().equals(passwordPlano))
                .orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));
    }
}
//hola
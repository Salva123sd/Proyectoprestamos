package com.mibanquito.dominio.puertos.entrada;

import com.mibanquito.dominio.modelo.Usuario;

public interface LoginCasoUso {
    Usuario login(String email, String passwordPlano);
}

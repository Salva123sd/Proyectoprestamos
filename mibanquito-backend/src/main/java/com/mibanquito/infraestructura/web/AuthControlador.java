package com.mibanquito.infraestructura.web;

import com.mibanquito.dominio.modelo.Usuario;
import com.mibanquito.dominio.puertos.entrada.LoginCasoUso;
import com.mibanquito.dominio.puertos.entrada.RegistrarUsuarioCasoUso;
import com.mibanquito.infraestructura.web.dto.LoginRequest;
import com.mibanquito.infraestructura.web.dto.LoginResponse;
import com.mibanquito.infraestructura.web.dto.RegistroRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthControlador {

    private final RegistrarUsuarioCasoUso registrar;
    private final LoginCasoUso login;

    public AuthControlador(RegistrarUsuarioCasoUso registrar, LoginCasoUso login) {
        this.registrar = registrar;
        this.login = login;
    }

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody RegistroRequest req) {
        Long id = registrar.registrar(req.nombreCompleto(), req.negocio(), req.email(), req.password());
        return ResponseEntity.ok(id);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
        Usuario u = login.login(req.email(), req.password());
        LoginResponse resp = new LoginResponse(
                u.getId(),
                u.getNombreCompleto(),
                u.getNegocio(),
                u.getEmail(),
                u.getRol()
        );
        return ResponseEntity.ok(resp);
    }
}

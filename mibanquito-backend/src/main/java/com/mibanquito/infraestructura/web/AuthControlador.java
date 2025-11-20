package com.mibanquito.infraestructura.web;

import com.mibanquito.dominio.modelo.Usuario;
import com.mibanquito.dominio.modelo.Usuario;
import com.mibanquito.dominio.puertos.entrada.LoginCasoUso;
import com.mibanquito.dominio.puertos.entrada.RegistrarUsuarioCasoUso;
import com.mibanquito.infraestructura.web.dto.LoginRequest;
import com.mibanquito.infraestructura.web.dto.LoginResponse;
import com.mibanquito.infraestructura.web.dto.RegistroRequest;
import com.mibanquito.security.jwt.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthControlador {

    private final RegistrarUsuarioCasoUso registrar;
    private final LoginCasoUso login;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthControlador(RegistrarUsuarioCasoUso registrar, LoginCasoUso login, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.registrar = registrar;
        this.login = login;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody RegistroRequest req) {
        Long id = registrar.registrar(req.nombreCompleto(), req.negocio(), req.email(), req.password());
        return ResponseEntity.ok(id);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.email(), req.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateJwtToken(authentication);

        Usuario u = login.login(req.email(), req.password());
        LoginResponse resp = new LoginResponse(
                u.getId(),
                u.getNombreCompleto(),
                u.getNegocio(),
                u.getEmail(),
                u.getRol(),
                jwt
        );
        return ResponseEntity.ok(resp);
    }
}

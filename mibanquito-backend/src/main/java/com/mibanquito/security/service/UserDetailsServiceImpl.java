package com.mibanquito.security.service;

import com.mibanquito.dominio.modelo.Usuario;
import com.mibanquito.infraestructura.persistencia.entidad.UsuarioEntidad;
import com.mibanquito.infraestructura.persistencia.repositorio.UsuarioRepositorioJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepositorioJPA usuarioRepositorio;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UsuarioEntidad entidad = usuarioRepositorio.findByEmail(email)
                .orElseThrow(() ->
                    new UsernameNotFoundException("Usuario no encontrado: " + email)
                );

        // convertir entidad → modelo de dominio Usuario (que implementa UserDetails)
        return new Usuario(
                entidad.getId(),
                entidad.getNombreCompleto(),
                entidad.getNegocio(),
                entidad.getEmail(),
                entidad.getPassword(),
                entidad.getRol()
        );
    }
}

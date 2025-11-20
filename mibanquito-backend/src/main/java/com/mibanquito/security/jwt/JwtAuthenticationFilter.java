package com.mibanquito.security.jwt;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

// Aseguramos que la importación de JwtUtil esté correcta
import com.mibanquito.security.jwt.JwtUtil; 
// Aseguramos que la importación del servicio de detalles de usuario esté correcta
import com.mibanquito.security.service.UserDetailsServiceImpl; 


/**
 * Filtro de autenticación JWT.
 * Se ejecuta una vez por cada petición para validar el token y autenticar al usuario.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);

            // 1. Validar si el token existe y es válido
            if (jwt != null && jwtUtil.validateJwtToken(jwt)) {

                // 2. Obtener el nombre de usuario (email) desde el token
                String email = jwtUtil.getUserNameFromJwtToken(jwt);

                // 3. Cargar los detalles del usuario
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                // 4. Crear el objeto de autenticación de Spring Security
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                // 5. Establecer la autenticación en el contexto de seguridad
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception e) {
            System.out.println("ERROR auth JWT: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Extrae el token JWT del encabezado 'Authorization' (ej: Bearer [token])
     */
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }
}
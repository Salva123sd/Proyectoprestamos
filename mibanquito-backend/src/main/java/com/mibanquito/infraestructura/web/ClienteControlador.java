package com.mibanquito.infraestructura.web;

import com.mibanquito.dominio.modelo.Cliente;
import com.mibanquito.dominio.puertos.entrada.CrearClienteCasoUso;
import com.mibanquito.dominio.puertos.entrada.ListarClientesCasoUso;
import com.mibanquito.dominio.puertos.entrada.ObtenerClienteCasoUso;
import com.mibanquito.infraestructura.web.dto.ClienteRequest;
import com.mibanquito.infraestructura.web.dto.ClienteResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteControlador {

    private final CrearClienteCasoUso crear;
    private final ListarClientesCasoUso listar;
    private final ObtenerClienteCasoUso obtener;

    public ClienteControlador(CrearClienteCasoUso crear, ListarClientesCasoUso listar, ObtenerClienteCasoUso obtener) {
        this.crear = crear;
        this.listar = listar;
        this.obtener = obtener;
    }

    @PostMapping
    public ResponseEntity<Long> crearCliente(@RequestBody ClienteRequest req) {
        Long id = crear.crear(
                req.nombre(),
                req.dni(),
                req.telefono(),
                req.direccion(),
                req.nota()
        );
        return ResponseEntity.ok(id);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listarClientes() {
        List<ClienteResponse> lista = listar.listar().stream()
                .map(c -> new ClienteResponse(
                        c.getId(),
                        c.getNombre(),
                        c.getDni(),
                        c.getTelefono(),
                        c.getDireccion(),
                        c.getNota()
                )).toList();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> obtenerCliente(@PathVariable Long id) {
        Cliente c = obtener.obtenerPorId(id);
        return ResponseEntity.ok(new ClienteResponse(
                c.getId(),
                c.getNombre(),
                c.getDni(),
                c.getTelefono(),
                c.getDireccion(),
                c.getNota()
        ));
    }
}

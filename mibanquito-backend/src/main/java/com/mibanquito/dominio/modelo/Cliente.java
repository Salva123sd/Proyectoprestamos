package com.mibanquito.dominio.modelo;

public class Cliente {

    private Long id;
    private String nombre;
    private String dni;
    private String telefono;
    private String direccion;
    private String nota;

    public Cliente(Long id, String nombre, String dni, String telefono, String direccion, String nota) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
        this.nota = nota;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDni() { return dni; }
    public String getTelefono() { return telefono; }
    public String getDireccion() { return direccion; }
    public String getNota() { return nota; }
}

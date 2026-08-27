package com.snpp.oikocrm.model;

import java.time.LocalDate;

public class Cliente {

    private int idCliente;
    private String nombre;
    private String email;
    private String telefono;
    private String empresa;
    private String direccion;
    private LocalDate fechaRegistro;

    public Cliente() {
    }

    public Cliente(String nombre, String email, String telefono, String empresa, String direccion) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.empresa = empresa;
        this.direccion = direccion;
    }

    public Cliente(int idCliente, String nombre, String email, String telefono,
                    String empresa, String direccion, LocalDate fechaRegistro) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.empresa = empresa;
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}

// Representa la estructura de los datos (la entidad) y el acceso a la base de datos a través del DAO
// encapsulando la información que viaja por la aplicación.
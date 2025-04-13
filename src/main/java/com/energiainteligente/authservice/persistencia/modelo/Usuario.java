package com.energiainteligente.authservice.persistencia.modelo;

import jakarta.persistence.*;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String googleId;
    private String nombre;
    private String correo;
    private String rol;


    public Long getId() {
        return id;
    }

    public String getGoogleId() {
        return googleId;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getRol() {
        return rol;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}


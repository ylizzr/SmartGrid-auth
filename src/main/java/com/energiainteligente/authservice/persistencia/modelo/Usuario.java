package com.energiainteligente.authservice.persistencia.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @Column (name = "id")
    private String id;

    @Column(name = "google_id")
    private String googleId;
    private String correo;
    private String rol; // "CLIENTE" o "EMPLEADO"

/*
    @OneToOne(mappedBy = "usuario")
    private Cliente cliente;

    @OneToOne(mappedBy = "usuario")
    private Empleado empleado;
*/

}

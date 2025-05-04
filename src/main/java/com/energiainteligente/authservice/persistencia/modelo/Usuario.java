package com.energiainteligente.authservice.persistencia.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "user")
public class Usuario {

    @Id
    @Column (name = "id")
    private String id;

    private String nombre;
    private String correo;
    private String rol;

}

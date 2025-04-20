package com.energiainteligente.authservice.persistencia.modelo;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    @Column (name = "cedula")
    private String cedula;

    private String nombre;
    private String correo;
    private String celular;

    @Column (name = "area_encargada")
    private String areaEncargada;
/*
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
*/
}


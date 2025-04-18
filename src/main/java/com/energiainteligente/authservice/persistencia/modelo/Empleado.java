package com.energiainteligente.authservice.persistencia.modelo;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;


@Getter
@Setter
@Table(name = "T_Empleados")
@Entity
public class Empleado {
    @Id
    private Long id;

    private String nombre;
    private String correo;
    private String cedula;
    private String celular;
    private String areaEncargada;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
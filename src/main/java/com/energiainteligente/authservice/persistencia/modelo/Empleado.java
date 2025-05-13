package com.energiainteligente.authservice.persistencia.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "empleado")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Empleado {

    @Id
    @Column (name = "cedula")
    private String cedula;

    private String usuario;

    private String contrasena;
    private String nombre;
    @Column (name = "area_encargada")
    private String areaEncargada;
}

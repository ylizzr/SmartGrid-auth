package com.energiainteligente.authservice.persistencia.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clientes")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Cliente {

    @Id
    @Column(name = "id")
    private String  id;

    private String nombre;
    @Column(name = "numero_cuenta")
    private String numeroCuenta;

    private String correo;
    private String direccion;
    private String celular;
}


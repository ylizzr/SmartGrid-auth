package com.energiainteligente.authservice.persistencia.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Table(name = "T_Clientes")
@Entity
public class Cliente {
    @Id
    private Long id;

    private String nombre;
    private String correoElectronico;
    private String numeroCuenta;
    private String celular;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
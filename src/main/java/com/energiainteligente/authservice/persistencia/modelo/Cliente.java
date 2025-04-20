package com.energiainteligente.authservice.persistencia.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @Column(name = "id")
    private String  id;

    private String nombre;
    @Column(name = "correo_electronico")
    private String correoElectronico;
    private String celular;

    @Column(name = "numero_cuenta")
    private String numeroCuenta;

   
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}

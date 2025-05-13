package com.energiainteligente.authservice.persistencia.repositorio;

import com.energiainteligente.authservice.persistencia.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepositorio extends JpaRepository<Cliente, String> {
    List<Cliente> findByCorreo(String correo);
}

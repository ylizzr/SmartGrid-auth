package com.energiainteligente.authservice.persistencia.repositorio;

import com.energiainteligente.authservice.persistencia.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepositorio extends JpaRepository<Cliente, String> {
    boolean existsByNumeroCuenta(String numeroCuenta);
}

package com.energiainteligente.authservice.persistencia.repositorio;

import com.energiainteligente.authservice.persistencia.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCorreoElectronico(String correo);
    Optional<Cliente> findByNumeroCuenta(String numeroCuenta);


    Optional<Cliente> findByUsuarioId(Long usuarioId);
    boolean existsByCorreoElectronico(String correo);
}
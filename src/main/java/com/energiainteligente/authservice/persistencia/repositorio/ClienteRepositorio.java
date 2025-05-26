package com.energiainteligente.authservice.persistencia.repositorio;

import com.energiainteligente.authservice.persistencia.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepositorio extends JpaRepository<Cliente, String> {
    @Query("SELECT c FROM Cliente c WHERE TRIM(LOWER(c.correo)) = TRIM(LOWER(:correo))")
    List<Cliente> findByCorreo(@Param("correo") String correo);

    //List<Cliente> findByCorreo(String correo);
    List<Cliente> findByNombreContainingIgnoreCase(String nombre);
    List<Cliente> findByCorreoContainingIgnoreCase(String correo);
    List<Cliente> findByNumeroCuentaContainingIgnoreCase(String numeroCuenta);

}

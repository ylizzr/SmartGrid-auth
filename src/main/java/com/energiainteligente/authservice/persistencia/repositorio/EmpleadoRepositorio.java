package com.energiainteligente.authservice.persistencia.repositorio;

import com.energiainteligente.authservice.persistencia.modelo.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpleadoRepositorio extends JpaRepository<Empleado, String> {
    Optional<Empleado> findByUsuario(String usuario);
}

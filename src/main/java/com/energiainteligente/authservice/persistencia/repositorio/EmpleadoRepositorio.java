package com.energiainteligente.authservice.persistencia.repositorio;

import com.energiainteligente.authservice.persistencia.modelo.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmpleadoRepositorio extends JpaRepository<Empleado, Long> {
    boolean existsByCorreoAndCedula(String correo, String cedula);
    Optional<Empleado> findByCorreo(String correo);
}
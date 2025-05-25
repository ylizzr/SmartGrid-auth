package com.energiainteligente.authservice.persistencia.repositorio;

import com.energiainteligente.authservice.persistencia.modelo.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmpleadoRepositorio extends JpaRepository<Empleado, String> {
    Optional<Empleado> findByUsuario(String usuario);

    List<Empleado> findByNombreContainingIgnoreCase(String nombre);
    List<Empleado> findByUsuarioContainingIgnoreCase(String usuario);
    List<Empleado> findByCedulaContainingIgnoreCase(String cedula);
}

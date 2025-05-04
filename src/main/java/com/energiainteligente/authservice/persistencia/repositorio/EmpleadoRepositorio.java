package com.energiainteligente.authservice.persistencia.repositorio;

import com.energiainteligente.authservice.persistencia.modelo.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmpleadoRepositorio extends JpaRepository<Empleado, String> {
    boolean existsByCedula(String cedula);
}

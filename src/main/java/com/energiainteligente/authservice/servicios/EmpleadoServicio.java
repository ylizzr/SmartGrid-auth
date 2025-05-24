package com.energiainteligente.authservice.servicios;

import com.energiainteligente.authservice.persistencia.modelo.Empleado;
import com.energiainteligente.authservice.persistencia.repositorio.EmpleadoRepositorio;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
@AllArgsConstructor
public class EmpleadoServicio {

    private static final Logger log = LogManager.getLogger(EmpleadoServicio.class);

    private final EmpleadoRepositorio empleadoRepositorio;
    private final PasswordEncoder passwordEncoder;

    public Empleado guardar(Empleado empleado) {
        try {
            if (empleado.getContrasena() != null) {
                empleado.setContrasena(passwordEncoder.encode(empleado.getContrasena()));
            }
            return empleadoRepositorio.save(empleado);
        } catch (Exception e) {
            log.error("Error al guardar empleado: {}", e.getMessage());
            throw new RuntimeException("Error al guardar empleado", e);
        }
    }

    public Optional<Empleado> buscarPorCedula(String cedula) {
        try {
            return empleadoRepositorio.findById(cedula);
        } catch (Exception e) {
            log.error("Error al buscar empleado por cédula {}: {}", cedula, e.getMessage());
            throw new RuntimeException("Error al buscar empleado", e);
        }
    }

    public List<Empleado> obtenerTodos() {
        try {
            return empleadoRepositorio.findAll();
        } catch (Exception e) {
            log.error("Error al obtener todos los empleados: {}", e.getMessage());
            throw new RuntimeException("Error al obtener empleados", e);
        }
    }

    public boolean borrarPorCedula(String cedula) {
        try {
            if (empleadoRepositorio.existsById(cedula)) {
                empleadoRepositorio.deleteById(cedula);
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("Error al borrar empleado con cédula {}: {}", cedula, e.getMessage());
            throw new RuntimeException("Error al borrar empleado", e);
        }
    }

    public Empleado actualizar(String cedula, Empleado empleadoActualizado) {
        try {
            return empleadoRepositorio.findById(cedula)
                    .map(empleadoExistente -> {
                        empleadoActualizado.setCedula(cedula);
                        if (empleadoActualizado.getContrasena() == null) {
                            empleadoActualizado.setContrasena(empleadoExistente.getContrasena());
                        } else if (!empleadoActualizado.getContrasena().equals(empleadoExistente.getContrasena())) {
                            empleadoActualizado.setContrasena(passwordEncoder.encode(empleadoActualizado.getContrasena()));
                        }
                        return empleadoRepositorio.save(empleadoActualizado);
                    })
                    .orElse(null);
        } catch (Exception e) {
            log.error("Error al actualizar empleado con cédula {}: {}", cedula, e.getMessage());
            throw new RuntimeException("Error al actualizar empleado", e);
        }
    }

    public boolean autenticarEmpleado(String usuario, String contrasena) {
        try {
            return empleadoRepositorio.findByUsuario(usuario)
                    .map(e -> passwordEncoder.matches(contrasena, e.getContrasena()))
                    .orElse(false);
        } catch (Exception e) {
            log.error("Error en autenticación para usuario {}: {}", usuario, e.getMessage());
            throw new RuntimeException("Error en autenticación", e);
        }
    }
}
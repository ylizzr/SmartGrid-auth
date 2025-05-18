package com.energiainteligente.authservice.servicios;
import com.energiainteligente.authservice.persistencia.modelo.Empleado;
import com.energiainteligente.authservice.persistencia.repositorio.EmpleadoRepositorio;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServicio {

    private final EmpleadoRepositorio empleadoRepositorio;
    private final PasswordEncoder passwordEncoder;

    public EmpleadoServicio(EmpleadoRepositorio empleadoRepositorio, PasswordEncoder passwordEncoder) {
        this.empleadoRepositorio = empleadoRepositorio;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean autenticarEmpleado(String usuario, String contrasena) {
        return empleadoRepositorio.findByUsuario(usuario)
                .map(e -> passwordEncoder.matches(contrasena, e.getContrasena()))
                .orElse(false);
    }

    public Empleado guardar(Empleado empleado) {
        if (empleado.getContrasena() != null) {
            empleado.setContrasena(passwordEncoder.encode(empleado.getContrasena()));
        }
        return empleadoRepositorio.save(empleado);
    }

    public List<Empleado> obtenerTodos() {
        return empleadoRepositorio.findAll();
    }

}

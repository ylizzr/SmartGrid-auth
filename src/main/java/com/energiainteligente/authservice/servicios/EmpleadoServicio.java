package com.energiainteligente.authservice.servicios;
import com.energiainteligente.authservice.persistencia.modelo.Empleado;
import com.energiainteligente.authservice.persistencia.repositorio.EmpleadoRepositorio;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
/*
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
}
*/
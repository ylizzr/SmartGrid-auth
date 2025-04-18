package com.energiainteligente.authservice.servicios;

import com.energiainteligente.authservice.persistencia.modelo.Empleado;
import com.energiainteligente.authservice.persistencia.repositorio.EmpleadoRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmpleadoServicio {

    private final EmpleadoRepositorio empleadoRepositorio;

    public EmpleadoServicio(EmpleadoRepositorio empleadoRepositorio) {
        this.empleadoRepositorio = empleadoRepositorio;
    }

    public boolean validarEmpleado(String correo, String cedula) {
        return empleadoRepositorio.existsByCorreoAndCedula(correo, cedula);
    }

    public Empleado obtenerPorCorreo(String correo) {
        return empleadoRepositorio.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
    }

    public Empleado registrarEmpleado(String nombre, String cedula, String correo, String areaEncargada) {
        Empleado empleado = new Empleado();
        empleado.setNombre(nombre);
        empleado.setCedula(cedula);
        empleado.setCorreo(correo);
        empleado.setAreaEncargada(areaEncargada);

        return empleadoRepositorio.save(empleado);
    }
}
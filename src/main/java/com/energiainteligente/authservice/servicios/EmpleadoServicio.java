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

    public boolean validarEmpleado(String cedula) {
        return empleadoRepositorio.existsById(cedula);
    }

    public Empleado obtenerPorCedula(String cedula) {
        return empleadoRepositorio.findById(cedula)
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

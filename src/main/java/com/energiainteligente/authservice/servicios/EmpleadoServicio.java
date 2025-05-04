package com.energiainteligente.authservice.servicios;

import com.energiainteligente.authservice.persistencia.repositorio.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoServicio {

    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;

    public boolean validarEmpleadoPorCedula(String cedula) {
        return empleadoRepositorio.existsByCedula(cedula);
    }
}

package com.energiainteligente.authservice.servicios;

import com.energiainteligente.authservice.persistencia.modelo.Empleado;
import com.energiainteligente.authservice.persistencia.repositorio.EmpleadoRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

   public void guardar(Empleado empleado) {
        empleadoRepositorio.save(empleado);
    }

     public List<Empleado> obtenerTodos() {
        return empleadoRepositorio.findAll();
    } 

     public void eliminarPorCedula(String cedula) {
        if (!empleadoRepositorio.existsById(cedula)) {
            throw new RuntimeException("Empleado no encontrado con cédula: " + cedula);
        }
        empleadoRepositorio.deleteById(cedula);
    } 
}

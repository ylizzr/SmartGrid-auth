package com.energiainteligente.authservice.controladores;

import com.energiainteligente.authservice.servicios.EmpleadoServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
public class EmpleadoControlador {

    private final EmpleadoServicio empleadoServicio;

    public EmpleadoControlador(EmpleadoServicio empleadoServicio) {
        this.empleadoServicio = empleadoServicio;
    }

    @GetMapping("/validar-empleado")
    public String mostrarFormularioValidacion() {
        return "validar-empleado";
    }

    @PostMapping("/validar-empleado")
    @ResponseBody
    public ResponseEntity<?> validarEmpleado(@RequestParam String cedula) {
        boolean existe = empleadoServicio.validarEmpleadoPorCedula(cedula);
        if (existe) {
            return ResponseEntity.ok().body(Collections.singletonMap("mensaje", "Conectado correctamente"));
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(Collections.singletonMap("mensaje", "Cédula no encontrada"));
        }
    }

}

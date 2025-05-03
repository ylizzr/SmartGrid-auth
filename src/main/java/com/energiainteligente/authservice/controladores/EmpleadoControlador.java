package com.energiainteligente.authservice.controladores;

import com.energiainteligente.authservice.persistencia.modelo.Empleado;
import com.energiainteligente.authservice.servicios.EmpleadoServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.util.Map;


import java.util.Collections;



@Controller
@RequestMapping("/empleado")
public class EmpleadoControlador {

    private final EmpleadoServicio empleadoServicio;

    public EmpleadoControlador(EmpleadoServicio empleadoServicio) {
        this.empleadoServicio = empleadoServicio;
    }

    @GetMapping("/validar")
    public String mostrarFormularioValidacion(@RequestParam String correo, Model model) {
        model.addAttribute("correo", correo);
        return "validar-empleado";
    }

    @PostMapping("/validar")
    @ResponseBody
    public ResponseEntity<Map<String, String>> procesarValidacion(@RequestParam String cedula) {
        try {
            empleadoServicio.validarEmpleado(cedula);
            return ResponseEntity.ok(Collections.singletonMap("mensaje", "Conectado correctamente"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje", e.getMessage()));
        }
    }



    @GetMapping("/gestion")
    public String listarEmpleados(Model model) {
        model.addAttribute("empleados", empleadoServicio.obtenerTodos());
        model.addAttribute("empleado", new Empleado());
        return "gestion-empleados";
    }

    @PostMapping("/guardar")
    public String guardarEmpleado(@ModelAttribute Empleado empleado) {
        empleadoServicio.guardar(empleado);
        return "redirect:/empleado/gestion";
    }

    @GetMapping("/editar/{cedula}")
    public String editarEmpleadoForm(@PathVariable String cedula, Model model) {
        Empleado empleado = empleadoServicio.obtenerPorCedula(cedula);
        model.addAttribute("empleado", empleado);
        model.addAttribute("empleados", empleadoServicio.obtenerTodos());
        return "gestion-empleados";
    }

    @PostMapping("/editar")
    public String actualizarEmpleado(@ModelAttribute Empleado empleado) {
        empleadoServicio.guardar(empleado); 
        return "redirect:/empleado/gestion";
    }

    @GetMapping("/eliminar/{cedula}")
    public String eliminarEmpleado(@PathVariable String cedula) {
        empleadoServicio.eliminarPorCedula(cedula);
        return "redirect:/empleado/gestion";
    }

}

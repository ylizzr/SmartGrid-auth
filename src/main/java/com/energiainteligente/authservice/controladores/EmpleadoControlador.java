package com.energiainteligente.authservice.controladores;

import com.energiainteligente.authservice.servicios.EmpleadoServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/empleado")
public class EmpleadoControlador {

    private final EmpleadoServicio empleadoServicio;

    public EmpleadoControlador(EmpleadoServicio empleadoServicio) {
        this.empleadoServicio = empleadoServicio;
    }

    @GetMapping("/validar")
    public String mostrarValidacion(@RequestParam String correo, Model model) {
        model.addAttribute("correo", correo);
        return "validar-empleado";
    }

    @PostMapping("/validar")
    public String procesarValidacion(
            @RequestParam String correo,
            @RequestParam String cedula,
            Model model,
            RedirectAttributes redirectAttributes) {

        try {
            if (empleadoServicio.validarEmpleado(correo, cedula)) {
                redirectAttributes.addAttribute("correo", correo);
                return "redirect:/empleado/bienvenida";
            } else {
                model.addAttribute("error", "Credenciales incorrectas");
                model.addAttribute("correo", correo);
                return "validar-empleado";
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/bienvenida")
    public String mostrarBienvenida(@RequestParam String correo, Model model) {
        model.addAttribute("empleado", empleadoServicio.obtenerPorCorreo(correo));
        return "bienvenida-empleado";
    }
}
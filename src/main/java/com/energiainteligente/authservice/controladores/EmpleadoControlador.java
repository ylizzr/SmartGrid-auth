package com.energiainteligente.authservice.controladores;

import com.energiainteligente.authservice.servicios.EmpleadoServicio;
import com.energiainteligente.authservice.servicios.UsuarioServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/empleado")
public class EmpleadoControlador {

    private final EmpleadoServicio empleadoServicio;
    private final UsuarioServicio usuarioServicio;

    public EmpleadoControlador(EmpleadoServicio empleadoServicio, UsuarioServicio usuarioServicio) {
        this.empleadoServicio = empleadoServicio;
        this.usuarioServicio = usuarioServicio;
    }

    @GetMapping("/validar")
    public String mostrarValidacion(@RequestParam String correo, Model model) {
        model.addAttribute("correo", correo);
        return "validar-empleado";
    }

    @PostMapping("/validar")
    @ResponseBody
    public ResponseEntity<?> procesarValidacion(@RequestParam String correo, @RequestParam String cedula) {
        try {
            if (empleadoServicio.validarEmpleado(correo, cedula)) {
                return ResponseEntity.ok(("/empleado/bienvenida?correo=" + correo));
            } else {
                return ResponseEntity.badRequest().body("Credenciales incorrectas");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/bienvenida")
    public String mostrarBienvenida(@RequestParam String correo, Model model) {
        model.addAttribute("empleado", empleadoServicio.obtenerPorCorreo(correo));
        return "bienvenida-empleado";
    }
}
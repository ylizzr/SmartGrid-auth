package com.energiainteligente.authservice.controladores;

import com.energiainteligente.authservice.persistencia.modelo.Empleado;
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


    @PostMapping("/validar")
    @ResponseBody
    public ResponseEntity<?> procesarValidacion(@RequestParam String cedula) {
        try {
            if (empleadoServicio.validarEmpleado(cedula)) {
                return ResponseEntity.ok(("/empleado/bienvenida?cedula=" + cedula));
            } else {
                return ResponseEntity.badRequest().body("Credenciales incorrectas");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/bienvenida")
    public String mostrarBienvenida(@RequestParam String cedula, Model model) {
        Empleado empleado = empleadoServicio.obtenerPorCedula(cedula);
        model.addAttribute("empleado", empleado);
        return "redirect:/bienvenida-empleado.html";
    }
}

package com.energiainteligente.authservice.controladores;

//import com.energiainteligente.authservice.servicios.EmpleadoServicio;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/empleado")
public class EmpleadoControlador {

    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "empleado-login";
    }
}
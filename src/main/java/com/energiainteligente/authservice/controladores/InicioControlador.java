package com.energiainteligente.authservice.controladores;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InicioControlador {

    @GetMapping("/")
    public String mostrarSeleccionRol() {
        return "seleccion-rol";
    }

    @PostMapping("/guardar-rol")
    public String guardarRol(@RequestParam("rol") String rol, HttpSession session) {
        session.setAttribute("rolSeleccionado", rol);
        return "login";
    }

    @GetMapping("/loginSuccess")
    public String redirigirSegunRol(HttpSession session) {
        String rol = (String) session.getAttribute("rolSeleccionado");

        if ("cliente".equalsIgnoreCase(rol)) {
            return "redirect:/validar-cliente";
        } else if ("empleado".equalsIgnoreCase(rol)) {
            return "redirect:/validar-empleado";
        } else {
            return "redirect:/";
        }
    }


}
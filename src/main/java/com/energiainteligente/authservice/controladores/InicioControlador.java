package com.energiainteligente.authservice.controladores;

import com.energiainteligente.authservice.persistencia.modelo.Usuario;
import com.energiainteligente.authservice.persistencia.repositorio.UsuarioRepositorio;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class InicioControlador {

    private final UsuarioRepositorio usuarioRepositorio;

    public InicioControlador(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/login.html";
    }

    @GetMapping("/redirect-by-role")
    public RedirectView redireccionarPorRol(Authentication authentication) {
        OAuth2User principal = (OAuth2User) authentication.getPrincipal();
        String correo = principal.getAttribute("email");

        Usuario usuario = usuarioRepositorio.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (usuario.getRol() == null || usuario.getRol().equalsIgnoreCase("PENDIENTE")) {
            return new RedirectView("/seleccion-rol.html?correo=" + correo);
        }

        if (usuario.getRol().equalsIgnoreCase("CLIENTE")) {
            return new RedirectView("/cliente/validar?correo=" + correo);
        } else if (usuario.getRol().equalsIgnoreCase("EMPLEADO")) {
            return new RedirectView("/empleado/validar?correo=" + correo);
        }
        return new RedirectView("/login.html");
    }
}
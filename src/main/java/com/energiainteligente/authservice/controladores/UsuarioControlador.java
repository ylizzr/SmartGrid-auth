package com.energiainteligente.authservice.controladores;

import com.energiainteligente.authservice.persistencia.modelo.Usuario;
import com.energiainteligente.authservice.servicios.UsuarioServicio;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;

    public UsuarioControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @GetMapping("/perfil")
    public Optional<Usuario> obtenerPerfil(@AuthenticationPrincipal OAuth2User principal) {
        String email = principal.getAttribute("email");
        return usuarioServicio.buscarPorCorreo(email);
    }
}

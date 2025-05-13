package com.energiainteligente.authservice.servicios;

import com.energiainteligente.authservice.persistencia.modelo.Usuario;
import com.energiainteligente.authservice.persistencia.repositorio.UsuarioRepositorio;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;

    public UsuarioServicio(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public Optional<Usuario> buscarPorCorreo(String correo) {
        return usuarioRepositorio.findByCorreo(correo);
    }

    public Usuario registrarSiNoExiste(String correo, String rol) {
        return usuarioRepositorio.findByCorreo(correo)
                .orElseGet(() -> usuarioRepositorio.save(new Usuario(null, correo, rol)));
    }
}

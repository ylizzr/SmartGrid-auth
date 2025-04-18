package com.energiainteligente.authservice.servicios;

import com.energiainteligente.authservice.persistencia.modelo.Usuario;
import com.energiainteligente.authservice.persistencia.repositorio.UsuarioRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;

    public UsuarioServicio(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public Usuario buscarPorCorreo(String correo) {
        return usuarioRepositorio.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Usuario crearUsuarioOAuth2(String googleId, String correo) {
        Usuario usuario = new Usuario();
        usuario.setGoogleId(googleId);
        usuario.setCorreo(correo);
        usuario.setRol("PENDIENTE");
        return usuarioRepositorio.save(usuario);
    }

    public Usuario actualizarRol(String correo, String rol) {
        Usuario usuario = buscarPorCorreo(correo);
        usuario.setRol(rol.toUpperCase());
        return usuarioRepositorio.save(usuario);
    }
}

package com.energiainteligente.authservice.servicios;

import com.energiainteligente.authservice.persistencia.modelo.Usuario;
import com.energiainteligente.authservice.persistencia.repositorio.UsuarioRepositorio;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UsuarioRepositorio usuarioRepositorio;

    public CustomOAuth2UserService(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String googleId = oAuth2User.getAttribute("sub");
        String nombre = oAuth2User.getAttribute("name");
        String correo = oAuth2User.getAttribute("email");

        // Buscar si ya existe
        Usuario usuario = usuarioRepositorio.findByGoogleId(googleId)
                .orElseGet(() -> {
                    Usuario nuevo = new Usuario();
                    nuevo.setGoogleId(googleId);
                    nuevo.setCorreo(correo);
                    nuevo.setNombre(nombre);
                    nuevo.setRol("USUARIO"); // por defecto
                    return nuevo;
                });

        usuario.setNombre(nombre); // Por si cambió en Google
        usuarioRepositorio.save(usuario);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + usuario.getRol())),
                oAuth2User.getAttributes(),
                "sub" // el identificador principal del usuario
        );
    }
}

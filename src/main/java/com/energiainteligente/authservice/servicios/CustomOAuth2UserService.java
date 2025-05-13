package com.energiainteligente.authservice.servicios;

import com.energiainteligente.authservice.persistencia.modelo.Usuario;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UsuarioServicio usuarioServicio;

    public CustomOAuth2UserService(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> attributes = oAuth2User.getAttributes();
        String correo = (String) attributes.get("email");  // Google devuelve "email", no "correo"
        String nombre = (String) attributes.get("name");

        // Registra el usuario como CLIENTE si no existe
        Usuario usuario = usuarioServicio.registrarSiNoExiste(correo, "CLIENTE");

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + usuario.getRol())),
                attributes,
                "email"
        );
    }
}

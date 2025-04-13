package com.energiainteligente.authservice.controladores;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class inicioControlador {

  /*  @GetMapping("/")
    public String inicio() {
        return "Bienvenido a energia inteligente";
    }*/

    @GetMapping("/")
    public RedirectView redireccionInicio() {
        return new RedirectView("/login.html");
    }


    @GetMapping("/usuario")
    public String usuario(@AuthenticationPrincipal OAuth2User principal) {
        return "Hola, " + principal.getAttribute("name");
    }
}

package com.energiainteligente.authservice.controladores;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class LoginRedireccionController {

    @GetMapping("/login-success")
    public void redirigirAlPortalClientes(HttpServletResponse response) throws IOException {
        response.sendRedirect("https://energiainteligente.ddns.net:8082/portal-clientes");
    }
}

package com.energiainteligente.authservice.controladores;

import com.energiainteligente.authservice.servicios.ClienteServicio;
import com.energiainteligente.authservice.persistencia.modelo.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteControlador {

    private final ClienteServicio clienteServicio;

    public ClienteControlador(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    @GetMapping("/mis-cuentas")
    public ResponseEntity<List<Cliente>> obtenerCuentas(@AuthenticationPrincipal OAuth2User user) {
        String correo = user.getAttribute("email");
        return ResponseEntity.ok(clienteServicio.obtenerClientesPorCorreo(correo));
    }
}
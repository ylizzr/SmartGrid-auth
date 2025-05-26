package com.energiainteligente.authservice.controladores;

import com.energiainteligente.authservice.servicios.ClienteServicio;
import com.energiainteligente.authservice.persistencia.modelo.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/clientes")
public class ClienteControlador {

    private static final Logger log = LoggerFactory.getLogger(ClienteControlador.class);

    private final ClienteServicio clienteServicio;

    public ClienteControlador(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    @GetMapping("/cuentas-asociadas")
    public ResponseEntity<?> obtenerCuentasAsociadas(@AuthenticationPrincipal OAuth2User user) {
        String correo = (String) user.getAttributes().get("email");

        log.info("Correo recibido del usuario autenticado: '{}'", correo);

        if (correo == null) {
            log.warn("No se pudo obtener el correo del usuario autenticado.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Cliente> cuentas = clienteServicio.obtenerClientesPorCorreo(correo);
        log.info("Cuentas asociadas encontradas: {}", cuentas.size());

        if (cuentas.isEmpty()) {
            log.warn("No se encontraron cuentas asociadas para el correo: {}", correo);
            return ResponseEntity.ok(Collections.singletonMap("message",
                    "No tienes cuentas asociadas. Contacta al administrador."));
        }

        return ResponseEntity.ok(cuentas);
    }
}

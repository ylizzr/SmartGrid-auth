package com.energiainteligente.authservice.controladores;

import com.energiainteligente.authservice.servicios.ClienteServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
public class ClienteControlador {

    private final ClienteServicio clienteServicio;

    public ClienteControlador(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    @GetMapping("/validar-cliente")
    public String mostrarFormularioValidacion() {
        return "validar-cliente";
    }

    @PostMapping("/validar-cliente")
    @ResponseBody
    public ResponseEntity<?> validarCliente(@RequestParam String numeroCuenta) {
        if (clienteServicio.validarClientePorNumeroCuenta(numeroCuenta)) {
            return ResponseEntity.ok().body(Collections.singletonMap("mensaje", "Cuenta válida. Bienvenido."));
        } else {
            return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje", "Número de cuenta no encontrado."));
        }
    }
}

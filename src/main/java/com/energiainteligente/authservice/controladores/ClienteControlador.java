package com.energiainteligente.authservice.controladores;

import com.energiainteligente.authservice.servicios.ClienteServicio;
import com.energiainteligente.authservice.servicios.UsuarioServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.util.Collections;


@Controller
@RequestMapping("/cliente")
public class ClienteControlador {

    private final ClienteServicio clienteServicio;
    private final UsuarioServicio usuarioServicio;

    public ClienteControlador(ClienteServicio clienteServicio, UsuarioServicio usuarioServicio) {
        this.clienteServicio = clienteServicio;
        this.usuarioServicio = usuarioServicio;
    }

    @PostMapping("/seleccionar")
    public RedirectView seleccionarRolCliente(@RequestParam String correo) {
        usuarioServicio.actualizarRol(correo, "CLIENTE");
        return new RedirectView("/cliente/validar-cliente?correo=" + correo);
    }


    @PostMapping("/validar-cliente")
    @ResponseBody
    public ResponseEntity<?> validarCliente(@RequestParam String numeroCuenta) {
        try {
            clienteServicio.validarCliente(numeroCuenta);
            return ResponseEntity.ok().body(Collections.singletonMap("redirectUrl", "/bienvenido-cliente.html"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/actualizar")
    public RedirectView actualizarCliente(
            @RequestParam String correo,
            @RequestParam String nombre,
            @RequestParam String Id,
            @RequestParam String celular) {

        clienteServicio.actualizarDatosCliente(correo, nombre,Id, celular);
        return new RedirectView("/bienvenido-cliente.html");
    }
}

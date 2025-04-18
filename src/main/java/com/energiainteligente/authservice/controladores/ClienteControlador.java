package com.energiainteligente.authservice.controladores;

import com.energiainteligente.authservice.servicios.ClienteServicio;
import com.energiainteligente.authservice.servicios.UsuarioServicio;
import jakarta.persistence.Id;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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
        return new RedirectView("/validar-cliente.html?correo=" + correo);
    }

    @PostMapping("/validar")
    public RedirectView validarCliente(@RequestParam String numeroCuenta) {
        if (clienteServicio.existeClientePorNumeroCuenta(numeroCuenta)) {
            return new RedirectView("/actualizar-cliente.html?numeroCuenta=" + numeroCuenta);
        } else {
            return new RedirectView("/acceso-denegado.html");
        }
    }


    @PostMapping("/actualizar")
    public RedirectView actualizarCliente(
            @RequestParam String nombre,
            @RequestParam Long Id,
            @RequestParam String celular,
            @RequestParam String correo) {

        clienteServicio.actualizarDatosCliente(correo, nombre, Id, celular);
        return new RedirectView("/bienvenida-cliente.html");
    }
}

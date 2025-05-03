package com.energiainteligente.authservice.controladores;

import com.energiainteligente.authservice.persistencia.modelo.Cliente;
import com.energiainteligente.authservice.servicios.ClienteServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collections;
import java.util.List;


@Controller
@RequestMapping("/cliente")
@AllArgsConstructor
public class ClienteControlador {

    private final ClienteServicio clienteServicio;

    @GetMapping("/validar")
    public String mostrarFormularioValidacion(@RequestParam String correo, Model model) {
       model.addAttribute("correo",correo);
       return "validar-cliente";
    }

    @PostMapping("/validar")
    @ResponseBody
    public ResponseEntity<?> validarCliente(@RequestParam String numeroCuenta) {
        try {
	    clienteServicio.validarCliente(numeroCuenta);
            return ResponseEntity.ok().body(Collections.singletonMap("mensaje", "Conectado correctamente"));
            
	} catch (RuntimeException  e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje", e.getMessage()));
       
        }
    }

    @PostMapping("/actualizar")
    public RedirectView actualizarCliente(
            @RequestParam String numeroCuenta,
            @RequestParam String nombre,
            @RequestParam String direccion,
            @RequestParam String celular) {

        clienteServicio.actualizarDatosCliente(numeroCuenta, nombre, direccion, celular);
        return new RedirectView("/bienvenida-cliente.html");
    }

    @GetMapping("/gestion")
    public String listarClientes (Model model) { 
        model.addAttribute("clientes", clienteServicio.obtenerTodosClientes());
        model.addAttribute("cliente", new Cliente());
        return "gestion-clientes";
    }

    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        clienteServicio.guardarCliente(cliente);
        return "redirect:/cliente/gestion";
    }

    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable String id, Model model) {
        Cliente cliente = clienteServicio.buscarClientePorId(id);
        model.addAttribute("cliente", cliente);
        model.addAttribute("clientes", clienteServicio.obtenerTodosClientes());
        return "gestion-clientes"; 
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable String id) {
        clienteServicio.eliminarClientePorId(id);
        return "redirect:/cliente/gestion";
    }
}

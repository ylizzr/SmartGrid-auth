package com.energiainteligente.authservice.controladores;

import com.energiainteligente.authservice.persistencia.modelo.Cliente;
import com.energiainteligente.authservice.persistencia.modelo.Empleado;
import com.energiainteligente.authservice.servicios.ClienteServicio;
import com.energiainteligente.authservice.servicios.EmpleadoServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class GestionControlador {

    private final ClienteServicio clienteServicio;
    private final EmpleadoServicio empleadoServicio;

    // --- CLIENTES ---
    @PostMapping("/clientes")
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteServicio.guardar(cliente));
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(clienteServicio.obtenerTodos());
    }

    // --- EMPLEADOS ---
    @PostMapping("/empleados")
    public ResponseEntity<Empleado> crearEmpleado(@RequestBody Empleado empleado) {
        return ResponseEntity.ok(empleadoServicio.guardar(empleado));
    }

    @GetMapping("/empleados")
    public ResponseEntity<List<Empleado>> listarEmpleados() {
        return ResponseEntity.ok(empleadoServicio.obtenerTodos());
    }
}

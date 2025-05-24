package com.energiainteligente.authservice.controladores;

import com.energiainteligente.authservice.persistencia.modelo.Cliente;
import com.energiainteligente.authservice.persistencia.modelo.Empleado;
import com.energiainteligente.authservice.servicios.ClienteServicio;
import com.energiainteligente.authservice.servicios.EmpleadoServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class GestionControlador {

    private final ClienteServicio clienteServicio;
    private final EmpleadoServicio empleadoServicio;

    // --- CLIENTES ---
    @PostMapping("/clientes")
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteServicio.guardar(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(clienteServicio.obtenerTodos());
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable String id) {
        Cliente cliente = clienteServicio.buscarPorId(id);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable String id, @RequestBody Cliente cliente) {
        Cliente actualizado = clienteServicio.actualizar(id, cliente);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable String id) {
        return clienteServicio.borrarPorId(id) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

    // --- EMPLEADOS ---
    @PostMapping("/empleados")
    public ResponseEntity<Empleado> crearEmpleado(@RequestBody Empleado empleado) {
        Empleado nuevoEmpleado = empleadoServicio.guardar(empleado);
        return new ResponseEntity<>(nuevoEmpleado, HttpStatus.CREATED);
    }

    @GetMapping("/empleados")
    public ResponseEntity<List<Empleado>> listarEmpleados() {
        return ResponseEntity.ok(empleadoServicio.obtenerTodos());
    }

    @GetMapping("/empleados/{cedula}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorCedula(@PathVariable String cedula) {
        Optional<Empleado> empleado = empleadoServicio.buscarPorCedula(cedula);
        return empleado.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/empleados/{cedula}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable String cedula, @RequestBody Empleado empleado) {
        Empleado actualizado = empleadoServicio.actualizar(cedula, empleado);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/empleados/{cedula}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable String cedula) {
        return empleadoServicio.borrarPorCedula(cedula) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}
package com.energiainteligente.authservice.controladores;

import com.energiainteligente.authservice.servicios.EmpleadoServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/empleado")
@AllArgsConstructor
public class EmpleadoAuthControlador {

    private final EmpleadoServicio empleadoServicio;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String usuario, @RequestParam String contrasena) {
        boolean valido = empleadoServicio.autenticarEmpleado(usuario, contrasena);
        if (valido) {
            return ResponseEntity.ok("OK");
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }
}
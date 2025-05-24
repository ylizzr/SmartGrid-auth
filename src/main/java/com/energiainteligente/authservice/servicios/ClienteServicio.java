package com.energiainteligente.authservice.servicios;

import com.energiainteligente.authservice.persistencia.modelo.Cliente;
import com.energiainteligente.authservice.persistencia.repositorio.ClienteRepositorio;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ClienteServicio {

    private static final Logger log = LogManager.getLogger(ClienteServicio.class);

    private final ClienteRepositorio clienteRepositorio;

    public List<Cliente> obtenerTodos() {
        try {
            return clienteRepositorio.findAll();
        } catch (Exception e) {
            log.error("Error al obtener todos los clientes: {}", e.getMessage());
            throw new RuntimeException("Error al obtener clientes", e);
        }
    }

    public Cliente guardar(Cliente cliente) {
        try {
            return clienteRepositorio.save(cliente);
        } catch (Exception e) {
            log.error("Error al guardar cliente: {}", e.getMessage());
            throw new RuntimeException("Error al guardar cliente", e);
        }
    }

    public Cliente buscarPorId(String id) {
        try {
            return clienteRepositorio.findById(id).orElse(null);
        } catch (Exception e) {
            log.error("Error al buscar cliente por ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Error al buscar cliente", e);
        }
    }

    public boolean borrar(Cliente cliente) {
        try {
            clienteRepositorio.delete(cliente);
            return true;
        } catch (Exception e) {
            log.error("Error al borrar el cliente: {}", e.getMessage());
            return false;
        }
    }

    public boolean borrarPorId(String id) {
        try {
            clienteRepositorio.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Error al borrar el cliente por ID {}: {}", id, e.getMessage());
            return false;
        }
    }

    public List<Cliente> obtenerClientesPorCorreo(String correo) {
        try {
            return clienteRepositorio.findByCorreo(correo);
        } catch (Exception e) {
            log.error("Error al buscar clientes por correo {}: {}", correo, e.getMessage());
            throw new RuntimeException("Error al buscar por correo", e);
        }
    }

    public Cliente actualizar(String id, Cliente clienteActualizado) {
        try {
            Cliente clienteExistente = buscarPorId(id);
            if (clienteExistente != null) {
                clienteActualizado.setId(id);
                return clienteRepositorio.save(clienteActualizado);
            }
            return null;
        } catch (Exception e) {
            log.error("Error al actualizar cliente con ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Error al actualizar cliente", e);
        }
    }
}



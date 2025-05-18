package com.energiainteligente.authservice.servicios;

import com.energiainteligente.authservice.persistencia.modelo.Cliente;
import com.energiainteligente.authservice.persistencia.modelo.Empleado;
import com.energiainteligente.authservice.persistencia.repositorio.ClienteRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServicio {

    private final ClienteRepositorio clienteRepositorio;

    public ClienteServicio(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    public List<Cliente> obtenerClientesPorCorreo(String correo) {
        return clienteRepositorio.findByCorreo(correo);
    }

    public Cliente guardar(Cliente cliente) {
        return clienteRepositorio.save(cliente);
    }

    public List<Cliente> obtenerTodos() {
        return clienteRepositorio.findAll();
    }



}

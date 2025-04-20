package com.energiainteligente.authservice.servicios;

import com.energiainteligente.authservice.persistencia.modelo.Cliente;
import com.energiainteligente.authservice.persistencia.repositorio.ClienteRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class ClienteServicio {

    private final ClienteRepositorio clienteRepositorio;

    public ClienteServicio(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    public boolean existeClientePorNumeroCuenta(String numeroCuenta) {
        return clienteRepositorio.findByNumeroCuenta(numeroCuenta).isPresent();
    }

    public Cliente validarCliente(String numeroCuenta) {
        return clienteRepositorio.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new RuntimeException("El número de cuenta no existe"));

     }


    public Cliente actualizarDatosCliente(String numeroCuenta, String nombre, String Id, String celular) {
        Cliente cliente = clienteRepositorio.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new RuntimeException("Número de cuenta no encontrado"));

        cliente.setNombre(nombre);
        cliente.setCelular(celular);
        return clienteRepositorio.save(cliente);
    }
}

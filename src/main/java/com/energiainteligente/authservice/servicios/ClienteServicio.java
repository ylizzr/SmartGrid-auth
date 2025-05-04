package com.energiainteligente.authservice.servicios;
import com.energiainteligente.authservice.persistencia.repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServicio {
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public boolean existePorNumeroCuenta(String numeroCuenta) {
        return clienteRepositorio.existsByNumeroCuenta(numeroCuenta);
    }
}
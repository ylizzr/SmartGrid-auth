package com.energiainteligente.authservice.servicios;

import com.energiainteligente.authservice.persistencia.modelo.Cliente;
import com.energiainteligente.authservice.persistencia.repositorio.ClienteRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    public Cliente actualizarDatosCliente(String numeroCuenta, String nombre, String direccion, String celular) {
        Optional<Cliente> clienteOptional = clienteRepositorio.findByNumeroCuenta(numeroCuenta);
        if (clienteOptional.isEmpty()) {
            throw new RuntimeException("Cliente no encontrado con el numero de cuenta: " + numeroCuenta);
        }
        Cliente cliente = clienteOptional.get();
        cliente.setNombre(nombre);
        cliente.setNombre(direccion);
        cliente.setCelular(celular);
        return clienteRepositorio.save(cliente);
    }


     public List<Cliente> obtenerTodosClientes() { 
         return clienteRepositorio.findAll();
     }

     public void guardarCliente(Cliente cliente) {
         clienteRepositorio.save(cliente);
     }
 
     public Cliente buscarClientePorId(String id) {
        return clienteRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
     }

     public void eliminarClientePorId(String id) {
         clienteRepositorio.deleteById(id);
     }

}

package com.energiainteligente.authservice.persistencia.repositorio;

import com.energiainteligente.authservice.persistencia.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreo(String correo);
}

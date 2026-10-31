package io.emit.domain.repository;

import io.emit.domain.entity.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository {
    Optional<Usuario> findByEmail(String email);
    Boolean existsByEmail(String email);
    Usuario save(Usuario usuario);
    Optional<Usuario> findById(UUID id);
}

package io.emit.infrastructure.persistence.repository;

import io.emit.infrastructure.persistence.entity.UsuarioJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataUsuarioRepository extends JpaRepository<UsuarioJpaEntity, UUID> {
    boolean existsByEmail(String email);
    Optional<UsuarioJpaEntity> findByEmail(String email);
}

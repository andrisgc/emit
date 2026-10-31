package io.emit.infrastructure.persistence.repository;

import io.emit.domain.entity.Usuario;
import io.emit.domain.repository.UsuarioRepository;
import io.emit.domain.valueobject.Email;
import io.emit.infrastructure.persistence.entity.UsuarioJpaEntity;
import io.emit.infrastructure.persistence.mapper.UsuarioEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {
    private final SpringDataUsuarioRepository jpaRepository;

    public UsuarioRepositoryImpl(SpringDataUsuarioRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioJpaEntity entity = UsuarioEntityMapper.toJpaEntity(usuario);
        UsuarioJpaEntity saved = jpaRepository.save(entity);

        return UsuarioEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return jpaRepository.findByEmail(email).map(UsuarioEntityMapper::toDomain);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    @Override
    public Optional<Usuario> findById(UUID id) {
        return jpaRepository.findById(id).map(UsuarioEntityMapper::toDomain);
    }
}

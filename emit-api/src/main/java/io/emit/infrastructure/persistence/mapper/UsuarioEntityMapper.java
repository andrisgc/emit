package io.emit.infrastructure.persistence.mapper;

import io.emit.domain.entity.Usuario;
import io.emit.domain.valueobject.Email;
import io.emit.infrastructure.persistence.entity.UsuarioJpaEntity;

public final class UsuarioEntityMapper {
    private UsuarioEntityMapper() {}

    public static UsuarioJpaEntity toJpaEntity(Usuario domain) {
        UsuarioJpaEntity entity = new UsuarioJpaEntity();

        entity.setId(domain.getId());
        entity.setNome(domain.getNome());
        entity.setEmail(domain.getEmail().getEndereco());
        entity.setSenhaHash(domain.getSenhaHash());
        entity.setEmailConfirmado(domain.isEmailConfirmado());
        entity.setTokenConfirmacao(domain.getTokenConfirmacao());
        entity.setTokenRecuperacao(domain.getTokenRecuperacao());
        entity.setTokenRecuperacaoExpira(domain.getTokenRecuperacaoExpira());
        entity.setTentativasLogin(domain.getTentativasLogin());
        entity.setAtualizadoEm(domain.getAtualizadoEm());
        entity.setAtivo(domain.isAtivo());
        entity.setDataCadastro(domain.getDataCadastro());
        entity.setAtualizadoEm(domain.getAtualizadoEm());

        return entity;
    }

    public static Usuario toDomain(UsuarioJpaEntity entity) {
        return new Usuario(
                entity.getId(),
                entity.getNome(),
                new Email(entity.getEmail()),
                entity.getSenhaHash(),
                entity.getDataCadastro(),
                entity.isAtivo(),
                entity.isEmailConfirmado(),
                entity.getTokenConfirmacao(),
                entity.getTokenRecuperacao(),
                entity.getTokenRecuperacaoExpira(),
                entity.getTentativasLogin(),
                entity.getBloqueadoAte(),
                entity.getAtualizadoEm()
        );
    }

}

package io.emit.application.usecase;

import io.emit.application.port.out.PasswordEncoderPort;
import io.emit.domain.entity.Usuario;
import io.emit.domain.exception.UsuarioJaCadastradoException;
import io.emit.domain.repository.UsuarioRepository;
import io.emit.domain.valueobject.Email;
import io.emit.interfaces.dto.RegistroRequest;

public class RegistrarUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoderPort passwordEncoder;

    public RegistrarUsuarioUseCase(UsuarioRepository usuarioRepository, PasswordEncoderPort passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario execute(RegistroRequest request) {
        Email email = new Email(request.email());

        if (Boolean.TRUE.equals(usuarioRepository.existsByEmail(email.getEndereco()))) {
            throw new UsuarioJaCadastradoException(email.getEndereco());
        }

        String senhaHash = passwordEncoder.encode(request.senha());
        Usuario novoUsuario = Usuario.criarNovo(request.nome(), email, senhaHash);

        return usuarioRepository.save(novoUsuario);
    }

}

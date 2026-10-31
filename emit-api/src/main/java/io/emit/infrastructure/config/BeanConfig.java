package io.emit.infrastructure.config;

import io.emit.application.port.out.PasswordEncoderPort;
import io.emit.application.usecase.RegistrarUsuarioUseCase;
import io.emit.domain.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public RegistrarUsuarioUseCase cadastrarUsuarioUseCase(
            UsuarioRepository usuarioRepository,
            PasswordEncoderPort passwordEncoder
    ) {
        return new RegistrarUsuarioUseCase(usuarioRepository, passwordEncoder);
    }
}

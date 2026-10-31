package io.emit.application.usecase;

import io.emit.application.port.out.PasswordEncoderPort;
import io.emit.domain.entity.Usuario;
import io.emit.domain.exception.UsuarioJaCadastradoException;
import io.emit.domain.repository.UsuarioRepository;
import io.emit.interfaces.dto.RegistroRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class RegistrarUsuarioUseCaseTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoderPort passwordEncoder;

    @InjectMocks
    private RegistrarUsuarioUseCase useCase;

    @Test
    @DisplayName("Deve registrar usuário com sucesso quando e-mail for inédito.")
    void deveRegistrarUsuarioComSucesso() {
        var request = new RegistroRequest("João Silva", "joao@email.com", "senha@123");

        when(usuarioRepository.existsByEmail("joao@email.com")).thenReturn(false);
        when(passwordEncoder.encode("senha@123")).thenReturn("hash_criptografado");
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Usuario usuario = useCase.execute(request);

        assertNotNull(usuario.getId());
        assertEquals("João Silva", usuario.getNome());
        assertEquals("joao@email.com", usuario.getEmail().getEndereco());
        assertFalse(usuario.isAtivo());
        assertFalse(usuario.isEmailConfirmado());
        assertNotNull(usuario.getTokenConfirmacao());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar registrar e-mail já existente")
    void deveLancarExcecaoQuandoEmailJaExiste() {
        var request = new RegistroRequest("Maria Silva", "maria@email.com", "senha@123");

        when(usuarioRepository.existsByEmail("maria@email.com")).thenReturn(true);

        assertThrows(UsuarioJaCadastradoException.class, () -> useCase.execute(request));
        verify(usuarioRepository, never()).save(any());
    }
}

package io.emit.domain.valueobject;

import io.emit.domain.exception.ValidacaoDominioException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    @DisplayName("Deve criar e-mail válido com sucesso.")
    void deveCriarEmailValido() {
        Email email = new Email("usuario@teste.com");
        assertEquals("usuario@teste.com", email.getEndereco());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "invalido", "teste@", "@teste.com", "teste@dominio"})
    @DisplayName("Deve lançar exceção para formatos inválidos de email.")
    void deveLancarExcecaoParaEmailInvalido(String valorInvalido) {
        assertThrows(ValidacaoDominioException.class, () -> new Email(valorInvalido));
    }



}

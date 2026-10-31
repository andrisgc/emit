package io.emit.interfaces.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.UUID;

public record UsuarioResponse (
        @Schema(description = "Identificador único do usuário")
        UUID id,

        @Schema(description = "Nome do usuário")
        String nome,

        @Schema(description = "E-mail do usuário")
        String email,

        @Schema(description = "Indica se o usuário está ativo")
        boolean ativo,

        @Schema(description = "Indica se o e-mail foi confirmado")
        boolean emailConfirmado,

        @Schema(description = "Data de cadastro")
        LocalDateTime dataCadastro
) {}

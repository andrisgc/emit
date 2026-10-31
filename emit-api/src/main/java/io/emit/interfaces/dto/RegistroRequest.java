package io.emit.interfaces.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegistroRequest(
        @Schema(description = "Nome completo do usuário", example = "João da Silva")
        @NotBlank(message = "O nome é obrigatório.")
        @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
        String nome,

        @Schema(description = "E-mail de acesso", example = "joao.silva@exemplo.com")
        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "Formato de e-mail inválido.")
        String email,

        @Schema(description = "Senha de acesso (mínimo 8 caracteres, maiúscula, minúscula, número e caractere especial)", example = "Senha@Forte123")
        @NotBlank(message = "A senha é obrigatória.")
        @Size(min = 8, max = 50, message = "A senha deve ter entre 8 e 50 caracteres.")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$",
                message = "A senha deve conter ao menos uma letra maiúscula, uma minúscula, um número e um caractere especial."
        )
        String senha
) {}

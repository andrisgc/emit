package io.emit.interfaces.rest;

import io.emit.application.usecase.RegistrarUsuarioUseCase;
import io.emit.domain.entity.Usuario;
import io.emit.interfaces.dto.RegistroRequest;
import io.emit.interfaces.dto.UsuarioResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Autenticação", description = "Endpoints de autenticação, registro e recuperação de acesso")
public class AuthController {
    private final RegistrarUsuarioUseCase registrarUsuarioUseCase;

    public AuthController(RegistrarUsuarioUseCase registrarUsuarioUseCase) {
        this.registrarUsuarioUseCase = registrarUsuarioUseCase;
    }

    @PostMapping("/registrar")
    @Operation(summary = "Registrar novo usuário", description = "Cria uma nova conta de usuário para o MEI.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "409", description = "E-mail já cadastrado")
    })
    public ResponseEntity<Map<String, Object>> registrar(@Valid @RequestBody RegistroRequest request) {
        Usuario usuario = registrarUsuarioUseCase.execute(request);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(usuario.getId())
                .toUri();

        return ResponseEntity.created(uri).body(Map.of(
                "id", usuario.getId(),
                "nome", usuario.getNome(),
                "email", usuario.getEmail().getEndereco(),
                "ativo", usuario.isAtivo(),
                "emailConfirmado", usuario.isEmailConfirmado(),
                "dataCadastro", usuario.getDataCadastro()
        ));
    }
}

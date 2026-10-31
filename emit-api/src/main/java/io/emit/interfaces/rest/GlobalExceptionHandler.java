package io.emit.interfaces.rest;

import io.emit.domain.exception.UsuarioJaCadastradoException;
import io.emit.domain.exception.ValidacaoDominioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.net.URI;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioJaCadastradoException.class)
    public ProblemDetail handleUsuarioJaCadastrado(UsuarioJaCadastradoException e) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
        problem.setTitle("Conflito de dados");
        problem.setType(URI.create("https://emit.io/errors/conflito"));
        problem.setProperty("timestamp", Instant.now());

        return problem;
    }

    @ExceptionHandler(ValidacaoDominioException.class)
    public ProblemDetail handleValidacaoDominio(ValidacaoDominioException e) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problem.setTitle("Erro de domínio");
        problem.setType(URI.create("https://emit.io/errors/validacao-dominio"));
        problem.setProperty("timestamp", Instant.now());

        return problem;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationErrors(MethodArgumentNotValidException e) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Campos inválidos na requisição.");
        problem.setTitle("Erro de validação");
        problem.setType(URI.create("https://emit.io/errors/validacao"));
        problem.setProperty("timestamp", Instant.now());

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        problem.setProperty("errors", errors);
        return problem;
    }
}

package io.emit.domain.entity;

import io.emit.domain.exception.ValidacaoDominioException;
import io.emit.domain.valueobject.Email;
import java.time.LocalDateTime;
import java.util.UUID;

public class Usuario {
    private final UUID id;
    private String nome;
    private final Email email;
    private String senhaHash;
    private final LocalDateTime dataCadastro;
    private boolean ativo;

    // Atributos de segurança
    private boolean emailConfirmado;
    private String tokenConfirmacao;
    private String tokenRecuperacao;
    private LocalDateTime tokenRecuperacaoExpira;
    private int tentativasLogin;
    private LocalDateTime bloqueadoAte;
    private LocalDateTime atualizadoEm;

    public Usuario(
            UUID id,
            String nome,
            Email email,
            String senhaHash,
            LocalDateTime dataCadastro,
            boolean ativo,
            boolean emailConfirmado,
            String tokenConfirmacao,
            String tokenRecuperacao,
            LocalDateTime tokenRecuperacaoExpira,
            int tentativasLogin,
            LocalDateTime bloqueadoAte,
            LocalDateTime atualizadoEm
    ) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senhaHash = senhaHash;
        this.dataCadastro = dataCadastro;
        this.ativo = ativo;
        this.emailConfirmado = emailConfirmado;
        this.tokenConfirmacao = tokenConfirmacao;
        this.tokenRecuperacao = tokenRecuperacao;
        this.tokenRecuperacaoExpira = tokenRecuperacaoExpira;
        this.tentativasLogin = tentativasLogin;
        this.bloqueadoAte = bloqueadoAte;
        this.atualizadoEm = atualizadoEm;
    }

    // Factory method para novo cadastro
    public static Usuario criarNovo(String nome, Email email, String senhaHash) {
        if (nome == null || nome.trim().length() < 2) {
            throw new ValidacaoDominioException("O nome deve ter no mínimo 2 caracteres.");
        }
        if (senhaHash == null || senhaHash.isBlank()) {
            throw new ValidacaoDominioException("O hash da senha não pode ser vazio.");
        }

        LocalDateTime now = LocalDateTime.now();
        String tokenConfirmacao = UUID.randomUUID().toString();

        return new Usuario(
                UUID.randomUUID(),
                nome.trim(),
                email,
                senhaHash,
                now,
                false,
                false,
                tokenConfirmacao,
                null,
                null,
                0,
                null,
                now
        );
    }

    public void ativar() {
        this.ativo = true;
        this.emailConfirmado = true;
        this.tokenConfirmacao = null;
        this.atualizadoEm = LocalDateTime.now();
    }

    public void inativar() {
        this.ativo = false;
        this.atualizadoEm = LocalDateTime.now();
    }

    public void alterarSenha(String novaSenhaHash) {
        if (novaSenhaHash == null || novaSenhaHash.isBlank()) {
            throw new ValidacaoDominioException("O hash da nova senha não pode ser vazio.");
        }

        this.senhaHash = novaSenhaHash;
        this.tokenRecuperacao = null;
        this.tokenRecuperacaoExpira = null;
        this.atualizadoEm = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Email getEmail() {
        return email;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public boolean isEmailConfirmado() {
        return emailConfirmado;
    }

    public String getTokenConfirmacao() {
        return tokenConfirmacao;
    }

    public String getTokenRecuperacao() {
        return tokenRecuperacao;
    }

    public LocalDateTime getTokenRecuperacaoExpira() {
        return tokenRecuperacaoExpira;
    }

    public int getTentativasLogin() {
        return tentativasLogin;
    }

    public LocalDateTime getBloqueadoAte() {
        return bloqueadoAte;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }
}

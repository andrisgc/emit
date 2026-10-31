CREATE TABLE usuario (
    id UUID PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    senha_hash VARCHAR(255) NOT NULL,
    email_confirmado BOOLEAN NOT NULL DEFAULT FALSE,
    token_confirmacao VARCHAR(255),
    token_recuperacao VARCHAR(255),
    token_recuperacao_expira TIMESTAMP,
    tentativas_login INTEGER NOT NULL DEFAULT 0,
    bloqueado_ate TIMESTAMP,
    ativo BOOLEAN NOT NULL DEFAULT FALSE,
    criado_em TIMESTAMP NOT NULL DEFAULT NOW(),
    atualizado_em TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_usuario_email ON usuario(email);
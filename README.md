<div align="center">
  <img src="docs/assets/emit-lockup.svg" alt="Emit" height="100" />
  <br/>
  <br/>

  [![Status](https://img.shields.io/badge/status-in%20development-yellow?style=flat-square&labelColor=1a1a1a)](.)
  [![License](https://img.shields.io/badge/license-BSL%201.1-blue?style=flat-square&labelColor=1a1a1a)](LICENSE)
  [![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&labelColor=1a1a1a&logo=openjdk&logoColor=white)](https://openjdk.org/projects/jdk/21/)
  [![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=flat-square&labelColor=1a1a1a&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
  [![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?style=flat-square&labelColor=1a1a1a&logo=postgresql&logoColor=white)](https://www.postgresql.org/)

</div>

---

O **Emit** é uma plataforma SaaS que automatiza a emissão de notas fiscais (NF-e/NFS-e) para microempreendedores individuais (MEI). A ferramenta integra-se diretamente à SEFAZ e aos sistemas municipais, gerencia o pagamento mensal do DAS e oferece acompanhamento de faturamento em tempo real — substituindo portais governamentais ineficientes por uma interface única, confiável e acessível.

> Desenvolvido para mais de 15 milhões de MEIs no Brasil que perdem horas todos os meses com o arquivamento manual de notas fiscais.

---

## Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
- [Tech Stack](#tech-stack)
- [API Reference](#api-reference)
- [Project Structure](#project-structure)
- [License](#license)

---

## Overview

### O problema

Os MEIs (microempreendedores individuais) brasileiros são legalmente obrigados a emitir notas fiscais e a pagar um tributo mensal (DAS). O processo oficial envolve:

- Navegação por portais municipais com experiência do usuário (UX) deficiente
- Preenchimento manual de campos em arquivos XML e gerenciamento de certificados digitais
- Acompanhamento dos prazos de vencimento do DAS sem o auxílio de lembretes automatizados
- Ausência de uma visão unificada da receita anual em relação ao limite legal de R$ 81.000

### O que o Emit faz

- Emite NF-e e NFS-e por meio de integração direta com a SEFAZ/prefeitura
- Assina arquivos XML utilizando o certificado digital A1 do próprio MEI (armazenado de forma criptografada)
- Envia o PDF da nota fiscal automaticamente ao cliente via e-mail e WhatsApp
- Gera lembretes do DAS com 10, 5 e 1 dia de antecedência da data de vencimento
- Monitora o faturamento acumulado no ano e emite alertas quando o valor se aproxima do limite anual do MEI

---

## Architecture

O Emit segue a **Clean Architecture**, tendo o **Domain-Driven Design** como base. A regra de dependência é rigorosamente aplicada: as camadas externas dependem das camadas internas, nunca o contrário.

```
┌─────────────────────────────────────────┐
│              interfaces/                │  REST controllers, DTOs, mappers
│  ┌───────────────────────────────────┐  │
│  │           application/            │  │  Use cases, ports (in/out)
│  │  ┌─────────────────────────────┐  │  │
│  │  │          domain/            │  │  │  Entities, value objects, rules
│  │  └─────────────────────────────┘  │  │
│  └───────────────────────────────────┘  │
│              infrastructure/            │  JPA, SEFAZ, messaging, jobs
└─────────────────────────────────────────┘
```

As escolhas técnicas e arquiteturais importantes do projeto serão armazenadas em [`docs/adr/`](docs/adr/).

---

## Tech Stack

| Layer | Technology | Reason |
|-------|-----------|--------|
| Language | Java 21 | LTS, virtual threads, records |
| Framework | Spring Boot 3.x | Mature ecosystem, wide adoption |
| Security | Spring Security + JWT RS256 | Token rotation, stateless auth |
| Database | PostgreSQL 16 | ACID, JSON support, reliability |
| Cache | Redis | Session store, rate limiting |
| Messaging | RabbitMQ | Async invoice dispatch, retry, DLQ |
| Frontend | React 18 + TypeScript + Vite | Type safety, fast builds |
| PDF | iText 7 | DANFSE/DANFE generation |
| XML signing | Bouncy Castle | A1 certificate operations |
| Testing | JUnit 5 + Mockito + Testcontainers | Unit + integration coverage |
| Infra | Docker + Railway → AWS | Simple start, clear migration path |

---

## API Reference

Documentação da API disponível via Swagger UI quando rodando localmente:
`http://localhost:8080/swagger-ui.html`

Endpoints:
```
POST   /api/v1/auth/register
POST   /api/v1/auth/login
POST   /api/v1/auth/refresh

GET    /api/v1/mei
PUT    /api/v1/mei

GET    /api/v1/clientes
POST   /api/v1/clientes
PUT    /api/v1/clientes/{id}
DELETE /api/v1/clientes/{id}

GET    /api/v1/notas
POST   /api/v1/notas
GET    /api/v1/notas/{id}
GET    /api/v1/notas/{id}/pdf
DELETE /api/v1/notas/{id}

GET    /api/v1/das
POST   /api/v1/das/{id}/pagar

GET    /api/v1/faturamento

POST   /api/v1/faturamento
GET    /api/v1/faturamento

```

---

## Project Structure

```
emit/
├── emit-api/                        # Spring Boot backend
│   └── src/main/java/io/emit/
│       ├── domain/
│       │   ├── entity/              # Mei, NotaFiscal, Das, Cliente...
│       │   ├── valueobject/         # Cnpj, Email, Endereco...
│       │   ├── repository/          # Repository interfaces
│       │   └── service/             # Domain services
│       ├── application/
│       │   ├── usecase/             # EmitirNotaFiscalUseCase...
│       │   └── port/
│       │       ├── in/              # Inbound port interfaces
│       │       └── out/             # Outbound port interfaces
│       ├── infrastructure/
│       │   ├── persistence/         # JPA implementations
│       │   ├── sefaz/               # SEFAZ/prefeitura clients
│       │   ├── messaging/           # RabbitMQ publisher/consumer
│       │   ├── email/               # Email service
│       │   ├── whatsapp/            # WhatsApp Business API
│       │   └── job/                 # Scheduled jobs
│       └── interfaces/
│           ├── rest/                # REST controllers
│           ├── dto/                 # Request/response DTOs
│           └── mapper/              # Domain <-> DTO mappers
├── emit-web/                        # React + TypeScript frontend
│   └── src/
│       ├── components/
│       ├── pages/
│       ├── hooks/
│       ├── services/                # API clients
│       └── types/
├── docs/
│   ├── adr/                         # Architecture Decision Records
│   ├── uml/                         # PlantUML diagrams
│   └── assets/
├── docker-compose.yml
├── .env.example
└── README.md
```

---

## Roadmap

### v1.0 — Core MVP
- [ ] Autenticação e cadastro de MEI
- [ ] Gestão básica de clientes
- [ ] Upload e criptografia do Certificado Digital A1
- [ ] Emissão de NFS-e (integração prefeitura/SEFAZ)
- [ ] Envio automático da nota fiscal por e-mail e WhatsApp

### v1.1.0 — Gestão Financeira & DAS
- [ ] Geração de DAS e lembretes de vencimento
- [ ] Dashboard de faturamento com alerta de limite anual
- [ ] Relatório fiscal e exportação de dados para o MEI

### v1.2.0 — Automações & Assistente WhatsApp
- [ ] Assistente conversacional via WhatsApp para solicitar emissão de notas
- [ ] Consultas rápidas de faturamento e DAS via chat

### v1.3.0 — Open Finance
- [ ] Integração com Open Finance para conciliação bancária automática

### v2.0.0 — Expansão Mobile & Ecossistema
- [ ] Aplicativo mobile nativo/híbrido (React Native)
- [ ] API Pública para contadores e integradores externos
- [ ] Suporte White-label para escritórios de contabilidade

---

## License

Distributed under the [Business Source License 1.1](LICENSE).  
Commercial use requires explicit permission from the author.  
See `LICENSE` for the full terms.

---
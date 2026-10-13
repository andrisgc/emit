# Backlog — Emit

> Plataforma de emissão de NF-e/NFS-e para MEIs brasileiros

**Versão:** 1.0  
**Data:** Outubro de 2026  
**Metodologia:** Kanban  
**Status:** Em refinamento

---

## Fase 1 — Fundação

> Objetivo: usuário consegue se cadastrar, fazer login, cadastrar seu CNPJ e acessar a plataforma.

| ID    | Nome                        | Tipo de Requisito | Prioridade | Descrição                                                                                                   | US   |
| ----- | --------------------------- | ----------------- | ---------- | ----------------------------------------------------------------------------------------------------------- | ---- |
| RF01  | Cadastro de usuário         | Funcional         | Must       | O sistema deve permitir o cadastro de usuário com nome, e-mail e senha.                                     | US01 |
| RF02  | Confirmação de e-mail       | Funcional         | Must       | O sistema deve enviar e-mail de confirmação após o cadastro, exigindo verificação antes do primeiro acesso. | US02 |
| RF03  | Login com JWT               | Funcional         | Must       | O sistema deve autenticar o usuário com e-mail e senha, retornando JWT de acesso e refresh token.           | US03 |
| RF04  | Bloqueio por tentativas     | Funcional         | Must       | O sistema deve bloquear a conta por 15 minutos após 5 tentativas de login incorretas.                       | US03 |
| RF05  | Recuperação de senha        | Funcional         | Must       | O sistema deve permitir recuperação de senha via link enviado ao e-mail, com expiração de 30 minutos.       | US04 |
| RF06  | Invalidação de sessões      | Funcional         | Must       | O sistema deve invalidar todas as sessões ativas ao trocar ou redefinir a senha.                            | US04 |
| RF07  | Cadastro do MEI             | Funcional         | Must       | O sistema deve permitir o cadastro dos dados do MEI: CNPJ, razão social, nome fantasia, endereço e CNAE.    | US05 |
| RF08  | Validação de CNPJ           | Funcional         | Must       | O sistema deve validar o CNPJ (dígitos verificadores) antes de salvar.                                      | US05 |
| RF09  | Preenchimento por CEP       | Funcional         | Must       | O sistema deve preencher o endereço automaticamente a partir do CEP via API dos Correios.                   | US05 |
| RF10  | Atualização de dados do MEI | Funcional         | Must       | O sistema deve permitir que o MEI atualize seus dados cadastrais a qualquer momento.                        | US06 |
| RNF04 | HTTPS obrigatório           | Não Funcional     | Must       | Toda comunicação deve ser via HTTPS/TLS 1.3.                                                                | —    |
| RNF06 | JWT RS256                   | Não Funcional     | Must       | A autenticação deve ser via JWT RS256 com refresh token rotativo.                                           | —    |

---

## Fase 2 — Clientes e Certificado Digital

> Objetivo: MEI consegue cadastrar seus clientes e fazer upload do certificado digital A1, habilitando a emissão de notas.

| ID    | Nome                              | Tipo de Requisito | Prioridade | Descrição                                                                                               | US   |
| ----- | --------------------------------- | ----------------- | ---------- | ------------------------------------------------------------------------------------------------------- | ---- |
| RF11  | Cadastro de cliente               | Funcional         | Must       | O sistema deve permitir o cadastro de clientes PF e PJ com nome, CPF/CNPJ, e-mail, telefone e endereço. | US07 |
| RF12  | Validação de CPF/CNPJ do cliente  | Funcional         | Must       | O sistema deve validar CPF e CNPJ do cliente (dígitos verificadores) antes de salvar.                   | US07 |
| RF13  | Identificação PF/PJ automática    | Funcional         | Must       | O sistema deve identificar automaticamente se o cliente é PF ou PJ com base no documento informado.     | US07 |
| RF14  | Edição de cliente                 | Funcional         | Must       | O sistema deve permitir edição de dados do cliente, exceto CPF/CNPJ.                                    | US08 |
| RF15  | Inativação de cliente             | Funcional         | Must       | O sistema deve permitir a inativação de clientes, impedindo sua seleção em novas notas.                 | US09 |
| RF16  | Reativação de cliente             | Funcional         | Must       | O sistema deve permitir a reativação de clientes inativados.                                            | US09 |
| RF17  | Upload do certificado A1          | Funcional         | Must       | O sistema deve permitir o upload do certificado digital A1 nos formatos .pfx ou .p12.                   | US10 |
| RF18  | Validação da senha do certificado | Funcional         | Must       | O sistema deve validar a senha do certificado antes de armazená-lo.                                     | US10 |
| RF19  | Armazenamento criptografado       | Funcional         | Must       | O sistema deve armazenar o certificado criptografado com AES-256, nunca em texto plano.                 | US10 |
| RF20  | Exibição de datas do certificado  | Funcional         | Must       | O sistema deve exibir data de emissão e expiração do certificado após o upload.                         | US11 |
| RF21  | Validação de CNPJ do certificado  | Funcional         | Must       | O sistema deve confirmar que o CNPJ do certificado corresponde ao MEI cadastrado.                       | US10 |
| RF22  | Bloqueio por certificado vencido  | Funcional         | Must       | O sistema deve bloquear a emissão de notas quando o certificado estiver vencido.                        | US11 |
| RNF05 | Criptografia em repouso           | Não Funcional     | Must       | Dados sensíveis devem ser criptografados em repouso com AES-256.                                        | —    |

---

## Fase 3 — Emissão de Nota Fiscal (Core)

> Objetivo: MEI consegue emitir sua primeira NFS-e, receber a autorização da prefeitura e baixar o PDF.

| ID    | Nome                                | Tipo de Requisito | Prioridade | Descrição                                                                                                           | US   |
| ----- | ----------------------------------- | ----------------- | ---------- | ------------------------------------------------------------------------------------------------------------------- | ---- |
| RF24  | Emissão de NFS-e                    | Funcional         | Must       | O sistema deve permitir a emissão de NFS-e com seleção de cliente, descrição, valor, competência e alíquota de ISS. | US12 |
| RF25  | Cálculo automático do ISS           | Funcional         | Must       | O sistema deve calcular automaticamente o valor do ISS com base na alíquota informada.                              | US12 |
| RF26  | Assinatura do XML                   | Funcional         | Must       | O sistema deve assinar o XML da nota com o certificado digital A1 do MEI antes de enviar.                           | US12 |
| RF27  | Envio para prefeitura               | Funcional         | Must       | O sistema deve enviar a nota para a prefeitura do município do MEI via integração com webservice.                   | US12 |
| RF28  | Retorno da autorização              | Funcional         | Must       | O sistema deve retornar número e código de verificação da nota após autorização.                                    | US13 |
| RF29  | Geração do PDF (DANFSE)             | Funcional         | Must       | O sistema deve gerar o PDF da nota automaticamente após autorização.                                                | US13 |
| RF32  | Atualização de status em tempo real | Funcional         | Must       | O sistema deve atualizar o status da nota em tempo real: Rascunho → Aguardando → Autorizada ou Rejeitada.           | US13 |
| RF33  | Exibição do motivo de rejeição      | Funcional         | Must       | O sistema deve exibir o motivo da rejeição em linguagem clara e permitir correção e reenvio.                        | US14 |
| RF34  | Idempotência no envio               | Funcional         | Must       | O sistema deve garantir idempotência no envio — impossível emitir nota duplicada.                                   | US12 |
| RF37  | Listagem de notas com filtros       | Funcional         | Must       | O sistema deve listar todas as notas do MEI com filtros por status, período e cliente.                              | US15 |
| RF38  | Download do PDF da nota             | Funcional         | Must       | O sistema deve permitir o download do PDF de qualquer nota autorizada.                                              | US13 |
| RNF01 | Tempo de resposta dos endpoints     | Não Funcional     | Must       | O tempo de resposta de qualquer endpoint deve ser inferior a 2 segundos.                                            | —    |
| RNF02 | Tempo de emissão                    | Não Funcional     | Must       | A emissão de nota deve ser confirmada em no máximo 10 segundos após o envio à prefeitura.                           | —    |

---

## Fase 4 — Envio automático ao cliente + Cancelamento

> Objetivo: MEI não precisa enviar a nota manualmente — o sistema entrega ao cliente por e-mail e WhatsApp automaticamente.

| ID   | Nome                                   | Tipo de Requisito | Prioridade | Descrição                                                                                                       | US   |
| ---- | -------------------------------------- | ----------------- | ---------- | --------------------------------------------------------------------------------------------------------------- | ---- |
| RF30 | Envio por e-mail ao cliente            | Funcional         | Must       | O sistema deve enviar a nota por e-mail automaticamente ao cliente após autorização.                            | US16 |
| RF31 | Envio por WhatsApp ao cliente          | Funcional         | Must       | O sistema deve enviar a nota por WhatsApp automaticamente ao cliente após autorização.                          | US17 |
| RF49 | Envio de nota por e-mail               | Funcional         | Must       | O sistema deve enviar a nota fiscal em PDF por e-mail ao cliente automaticamente após autorização.              | US16 |
| RF50 | Envio de nota por WhatsApp             | Funcional         | Must       | O sistema deve enviar a nota fiscal em PDF por WhatsApp ao cliente automaticamente após autorização.            | US17 |
| RF51 | Reenvio manual                         | Funcional         | Must       | O sistema deve permitir o reenvio manual da nota por e-mail ou WhatsApp a qualquer momento.                     | US18 |
| RF52 | Controle de status de envio            | Funcional         | Must       | O sistema deve registrar o status de envio de cada notificação e tentar novamente até 3 vezes em caso de falha. | US18 |
| RF35 | Cancelamento de nota                   | Funcional         | Must       | O sistema deve permitir o cancelamento de nota autorizada dentro do prazo permitido pelo município.             | US19 |
| RF36 | Notificação de cancelamento ao cliente | Funcional         | Must       | O sistema deve notificar o cliente por e-mail sobre o cancelamento da nota.                                     | US19 |

---

## Fase 5 — DAS e Painel de Faturamento

> Objetivo: MEI tem visibilidade financeira completa — sabe quanto faturou, quanto falta para o limite e nunca perde o DAS.

| ID    | Nome                             | Tipo de Requisito | Prioridade | Descrição                                                                                                                  | US   |
| ----- | -------------------------------- | ----------------- | ---------- | -------------------------------------------------------------------------------------------------------------------------- | ---- |
| RF39  | Geração automática do DAS        | Funcional         | Must       | O sistema deve gerar o DAS automaticamente no dia 1º de cada mês para o MEI.                                               | US20 |
| RF40  | Exibição do DAS                  | Funcional         | Must       | O sistema deve exibir valor, competência, data de vencimento e código de barras do DAS.                                    | US20 |
| RF41  | Marcar DAS como pago             | Funcional         | Must       | O sistema deve permitir que o MEI marque o DAS como pago manualmente.                                                      | US21 |
| RF42  | Alertas de vencimento do DAS     | Funcional         | Must       | O sistema deve enviar alertas de vencimento do DAS com 10, 5 e 1 dia de antecedência por e-mail e WhatsApp.                | US22 |
| RF43  | Histórico do DAS                 | Funcional         | Must       | O sistema deve listar o histórico de DAS de todos os meses com status e data de pagamento.                                 | US23 |
| RF44  | Faturamento mensal e anual       | Funcional         | Must       | O sistema deve exibir o faturamento do mês atual e dos últimos 12 meses.                                                   | US24 |
| RF45  | Barra de progresso do limite     | Funcional         | Must       | O sistema deve exibir o total acumulado no ano e quanto falta para o limite de R$ 81.000,00 com barra de progresso visual. | US25 |
| RF46  | Alertas de limite de faturamento | Funcional         | Must       | O sistema deve enviar alerta ao MEI quando o faturamento atingir 75%, 90% e 95% do limite anual.                           | US26 |
| RF47  | Bloqueio ao atingir limite       | Funcional         | Must       | O sistema deve bloquear a emissão de novas notas ao atingir 100% do limite anual e notificar o MEI.                        | US26 |
| RF48  | Últimas notas no painel          | Funcional         | Must       | O sistema deve listar as últimas notas emitidas com valor e status no painel.                                              | US24 |
| RNF03 | Disponibilidade mínima           | Não Funcional     | Must       | O sistema deve ter disponibilidade mínima de 99,5% mensais.                                                                | —    |

---

## Fase 6 — Alertas de Certificado + Conformidade

> Objetivo: sistema avisa o MEI antes que o certificado expire e está em conformidade com LGPD e segurança.

| ID    | Nome                                | Tipo de Requisito | Prioridade | Descrição                                                                                                 | US   |
| ----- | ----------------------------------- | ----------------- | ---------- | --------------------------------------------------------------------------------------------------------- | ---- |
| RF23  | Alertas de expiração do certificado | Funcional         | Should     | O sistema deve enviar alertas de expiração do certificado com 60, 30 e 15 dias de antecedência.           | US27 |
| RNF07 | Rate limiting                       | Não Funcional     | Must       | O sistema deve aplicar rate limiting de 100 requisições/minuto por usuário autenticado.                   | —    |
| RNF08 | Conformidade com LGPD               | Não Funcional     | Must       | O sistema deve estar em conformidade com a LGPD — consentimento, portabilidade e direito ao esquecimento. | —    |
| RNF09 | Retenção de dados fiscais           | Não Funcional     | Must       | Dados fiscais devem ser retidos por no mínimo 5 anos.                                                     | —    |
| RNF10 | Cobertura de testes                 | Não Funcional     | Should     | A cobertura de testes deve ser de no mínimo 80% nas camadas de domínio e aplicação.                       | —    |
| RNF11 | Interface responsiva                | Não Funcional     | Must       | A interface deve ser responsiva — funcional em desktop, tablet e mobile.                                  | —    |
| RNF12 | Tempo de onboarding                 | Não Funcional     | Should     | O tempo de onboarding deve ser inferior a 5 minutos (cadastro até primeira nota emitida).                 | —    |

---

## User Stories

|ID|Descrição|
|---|---|
|US01|Como MEI, eu quero criar uma conta com e-mail e senha, para que eu possa acessar a plataforma e gerenciar minhas notas fiscais.|
|US02|Como MEI, eu quero confirmar meu e-mail após o cadastro, para que minha conta fique segura e ativa.|
|US03|Como MEI cadastrado, eu quero fazer login com e-mail e senha, para que eu possa acessar minha conta com segurança.|
|US04|Como MEI, eu quero recuperar minha senha via e-mail, para que eu não perca acesso à minha conta.|
|US05|Como MEI, eu quero cadastrar meu CNPJ e dados da empresa, para que o sistema possa emitir notas fiscais corretamente.|
|US06|Como MEI, eu quero atualizar meus dados cadastrais, para que minhas informações estejam sempre corretas.|
|US07|Como MEI, eu quero cadastrar meus clientes PF e PJ, para que eu possa selecionar o destinatário ao emitir uma nota fiscal.|
|US08|Como MEI, eu quero editar os dados de um cliente, para que minha base de clientes fique sempre atualizada.|
|US09|Como MEI, eu quero inativar ou reativar um cliente, para que minha lista esteja organizada e sem entradas obsoletas.|
|US10|Como MEI, eu quero cadastrar meu certificado digital A1, para que o sistema possa assinar as notas fiscais em meu nome.|
|US11|Como MEI, eu quero visualizar a data de expiração do meu certificado digital, para que eu possa renová-lo antes de perder a capacidade de emitir notas.|
|US12|Como MEI prestador de serviços, eu quero emitir uma NFS-e, para que eu possa comprovar a prestação de serviço ao meu cliente.|
|US13|Como MEI, eu quero acompanhar o status da nota em tempo real e baixar o PDF após autorização, para que eu tenha controle das minhas emissões.|
|US14|Como MEI, eu quero ver o motivo da rejeição da nota e corrigi-la, para que eu possa resolver o problema sem precisar de suporte.|
|US15|Como MEI, eu quero filtrar minhas notas por status, período e cliente, para que eu encontre rapidamente o que preciso.|
|US16|Como MEI, eu quero que o sistema envie a nota por e-mail automaticamente ao meu cliente, para que eu não precise fazer isso manualmente.|
|US17|Como MEI, eu quero que o sistema envie a nota por WhatsApp automaticamente ao meu cliente, para que ele receba de forma rápida e conveniente.|
|US18|Como MEI, eu quero reenviar uma nota manualmente por e-mail ou WhatsApp, para que meu cliente receba mesmo se o envio automático falhar.|
|US19|Como MEI, eu quero cancelar uma nota fiscal já autorizada, para que eu possa corrigir um lançamento incorreto.|
|US20|Como MEI, eu quero visualizar e gerar o boleto do DAS todo mês, para que eu mantenha minha situação fiscal regular.|
|US21|Como MEI, eu quero marcar o DAS como pago, para que meu histórico de pagamentos fique atualizado.|
|US22|Como MEI, eu quero ser avisado antes do vencimento do DAS, para que eu nunca pague multa por atraso.|
|US23|Como MEI, eu quero consultar o histórico de DAS de todos os meses, para que eu tenha controle da minha regularidade fiscal.|
|US24|Como MEI, eu quero visualizar meu faturamento mensal e anual, para que eu acompanhe minha evolução financeira.|
|US25|Como MEI, eu quero ver quanto falta para atingir o limite de R$ 81.000,00, para que eu me planeje antes de ultrapassar o teto do MEI.|
|US26|Como MEI, eu quero ser alertado quando estiver próximo do limite anual, para que eu tome decisões antes de perder o status de MEI.|
|US27|Como MEI, eu quero ser avisado quando meu certificado digital estiver próximo do vencimento, para que eu possa renová-lo antes de perder a capacidade de emitir notas.|

---

_Documento mantido em `/docs/backlog.md` — atualizar a cada nova entrega._

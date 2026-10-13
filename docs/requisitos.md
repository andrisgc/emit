# Requisitos de Software — Emit

> Plataforma de emissão de NF-e/NFS-e para MEIs brasileiros

**Versão:** 1.0  
**Data:** Outubro de 2026  
**Status:** Em revisão

---

## 1. Onboarding, Autenticação e Conta

|ID|Descrição|
|---|---|
|RF01|O sistema deve permitir o cadastro de usuário com nome, e-mail e senha. (MH)|
|RF02|O sistema deve enviar e-mail de confirmação após o cadastro, exigindo verificação antes do primeiro acesso. (MH)|
|RF03|O sistema deve autenticar o usuário com e-mail e senha, retornando JWT de acesso e refresh token. (MH)|
|RF04|O sistema deve bloquear a conta por 15 minutos após 5 tentativas de login incorretas. (MH)|
|RF05|O sistema deve permitir recuperação de senha via link enviado ao e-mail, com expiração de 30 minutos. (MH)|
|RF06|O sistema deve invalidar todas as sessões ativas ao trocar ou redefinir a senha. (MH)|

---

## 2. Cadastro do MEI

|ID|Descrição|
|---|---|
|RF07|O sistema deve permitir o cadastro dos dados do MEI: CNPJ, razão social, nome fantasia, endereço completo e atividade principal (CNAE). (MH)|
|RF08|O sistema deve validar o CNPJ (dígitos verificadores) antes de salvar. (MH)|
|RF09|O sistema deve preencher o endereço automaticamente a partir do CEP informado via API dos Correios. (MH)|
|RF10|O sistema deve permitir que o MEI atualize seus dados cadastrais a qualquer momento. (MH)|

---

## 3. Gestão de Clientes

|ID|Descrição|
|---|---|
|RF11|O sistema deve permitir o cadastro de clientes PF e PJ com nome/razão social, CPF/CNPJ, e-mail, telefone e endereço. (MH)|
|RF12|O sistema deve validar CPF e CNPJ do cliente (dígitos verificadores) antes de salvar. (MH)|
|RF13|O sistema deve identificar automaticamente se o cliente é PF ou PJ com base no documento informado. (MH)|
|RF14|O sistema deve permitir edição de dados do cliente, exceto CPF/CNPJ. (MH)|
|RF15|O sistema deve permitir a inativação de clientes, impedindo sua seleção em novas notas sem excluí-los permanentemente. (MH)|
|RF16|O sistema deve permitir a reativação de clientes inativados. (MH)|

---

## 4. Certificado Digital

|ID|Descrição|
|---|---|
|RF17|O sistema deve permitir o upload do certificado digital A1 do MEI nos formatos .pfx ou .p12. (MH)|
|RF18|O sistema deve validar a senha do certificado antes de armazená-lo. (MH)|
|RF19|O sistema deve armazenar o certificado criptografado com AES-256, nunca em texto plano. (MH)|
|RF20|O sistema deve exibir data de emissão e expiração do certificado após o upload. (MH)|
|RF21|O sistema deve confirmar que o CNPJ do certificado corresponde ao MEI cadastrado antes de salvar. (MH)|
|RF22|O sistema deve bloquear a emissão de notas quando o certificado estiver vencido. (MH)|
|RF23|O sistema deve enviar alertas de expiração do certificado com 60, 30 e 15 dias de antecedência por e-mail e WhatsApp. (SH)|

---

## 5. Emissão de Nota Fiscal

|ID|Descrição|
|---|---|
|RF24|O sistema deve permitir a emissão de NFS-e (serviços) com seleção de cliente, descrição, valor, competência e alíquota de ISS. (MH)|
|RF25|O sistema deve calcular automaticamente o valor do ISS com base na alíquota informada. (MH)|
|RF26|O sistema deve assinar o XML da nota com o certificado digital A1 do MEI antes de enviar. (MH)|
|RF27|O sistema deve enviar a nota para a prefeitura do município do MEI via integração com webservice. (MH)|
|RF28|O sistema deve retornar número e código de verificação da nota após autorização pela prefeitura. (MH)|
|RF29|O sistema deve gerar o PDF da nota (DANFSE) automaticamente após autorização. (MH)|
|RF30|O sistema deve enviar a nota por e-mail automaticamente ao cliente após autorização. (MH)|
|RF31|O sistema deve enviar a nota por WhatsApp automaticamente ao cliente após autorização. (MH)|
|RF32|O sistema deve atualizar o status da nota em tempo real: Rascunho → Aguardando → Autorizada ou Rejeitada. (MH)|
|RF33|O sistema deve exibir o motivo da rejeição em linguagem clara e permitir correção e reenvio. (MH)|
|RF34|O sistema deve garantir idempotência no envio — impossível emitir nota duplicada. (MH)|
|RF35|O sistema deve permitir o cancelamento de nota autorizada dentro do prazo permitido pelo município. (MH)|
|RF36|O sistema deve notificar o cliente por e-mail sobre o cancelamento da nota. (MH)|
|RF37|O sistema deve listar todas as notas do MEI com filtros por status, período e cliente. (MH)|
|RF38|O sistema deve permitir o download do PDF de qualquer nota autorizada. (MH)|

---

## 6. Gestão do DAS

|ID|Descrição|
|---|---|
|RF39|O sistema deve gerar o DAS automaticamente no dia 1º de cada mês para o MEI. (MH)|
|RF40|O sistema deve exibir valor, competência, data de vencimento e código de barras do DAS. (MH)|
|RF41|O sistema deve permitir que o MEI marque o DAS como pago manualmente. (MH)|
|RF42|O sistema deve enviar alertas de vencimento do DAS com 10, 5 e 1 dia de antecedência por e-mail e WhatsApp. (MH)|
|RF43|O sistema deve listar o histórico de DAS de todos os meses com status e data de pagamento. (MH)|

---

## 7. Painel de Faturamento

|ID|Descrição|
|---|---|
|RF44|O sistema deve exibir o faturamento do mês atual e dos últimos 12 meses. (MH)|
|RF45|O sistema deve exibir o total acumulado no ano e quanto falta para o limite de R$ 81.000,00 com barra de progresso visual. (MH)|
|RF46|O sistema deve enviar alerta ao MEI quando o faturamento atingir 75%, 90% e 95% do limite anual. (MH)|
|RF47|O sistema deve bloquear a emissão de novas notas ao atingir 100% do limite anual e notificar o MEI. (MH)|
|RF48|O sistema deve listar as últimas notas emitidas com valor e status no painel. (MH)|

---

## 8. Notificações

|ID|Descrição|
|---|---|
|RF49|O sistema deve enviar a nota fiscal em PDF por e-mail ao cliente automaticamente após autorização. (MH)|
|RF50|O sistema deve enviar a nota fiscal em PDF por WhatsApp ao cliente automaticamente após autorização. (MH)|
|RF51|O sistema deve permitir o reenvio manual da nota por e-mail ou WhatsApp a qualquer momento. (MH)|
|RF52|O sistema deve registrar o status de envio de cada notificação (enviada, falhou) e tentar novamente até 3 vezes. (MH)|

---

## Requisitos Não Funcionais

|ID|Descrição|
|---|---|
|RNF01|O tempo de resposta de qualquer endpoint deve ser inferior a 2 segundos em condições normais. (MH)|
|RNF02|A emissão de nota deve ser confirmada em no máximo 10 segundos após o envio para a SEFAZ/Prefeitura. (MH)|
|RNF03|O sistema deve ter disponibilidade mínima de 99,5% mensais. (MH)|
|RNF04|Toda comunicação deve ser via HTTPS/TLS 1.3. (MH)|
|RNF05|Dados sensíveis (CNPJ, certificado digital) devem ser criptografados em repouso com AES-256. (MH)|
|RNF06|A autenticação deve ser via JWT RS256 com refresh token rotativo. (MH)|
|RNF07|O sistema deve aplicar rate limiting de 100 requisições/minuto por usuário autenticado. (MH)|
|RNF08|O sistema deve estar em conformidade com a LGPD — consentimento, portabilidade e direito ao esquecimento. (MH)|
|RNF09|Dados fiscais devem ser retidos por no mínimo 5 anos. (MH)|
|RNF10|A cobertura de testes deve ser de no mínimo 80% nas camadas de domínio e aplicação. (SH)|
|RNF11|A interface deve ser responsiva — funcional em desktop, tablet e mobile. (MH)|
|RNF12|O tempo de onboarding (cadastro até primeira nota emitida) deve ser inferior a 5 minutos. (SH)|

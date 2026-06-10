# Questionário de Acolhimento - APS Rondônia

## Sobre o Projeto
Este sistema é uma aplicação web desenvolvida como parte de uma Iniciação Científica para otimizar a coleta e análise de dados sobre o acolhimento na Atenção Primária à Saúde (APS) em Rondônia. O objetivo é permitir que cidadãos avaliem o atendimento recebido, gerando dados estruturados para análise de políticas públicas.

## Tecnologias Utilizadas
- **Backend:** Java com Spring Boot.
- **Frontend:** HTML5, CSS3 (Bootstrap-inspired) e JavaScript (Vanilla).
- **Banco de Dados:** Firebase Firestore (NoSQL).
- **Documentação de API:** OpenAPI / Swagger.

## Arquitetura
A aplicação segue o padrão de arquitetura Cliente-Servidor:
- **API:** Responsável por validar as 27 variáveis de resposta e gerenciar a persistência no Firestore.
- **Frontend:** Interface responsiva que consome a API através de requisições assíncronas (Fetch API).

## Como rodar localmente

### Pré-requisitos
- JDK 17+
- Maven
- VS Code (ou sua IDE de preferência)

### Configuração
1. Clone o repositório: `git clone [url-do-seu-repositorio]`
2. Configure o ambiente do Firebase colocando seu arquivo `firebase.key` em `src/main/resources`.
3. Rode o backend via Maven: `mvn spring-boot:run`
4. Abra a pasta `frontend` no VS Code e utilize a extensão **Live Server** para visualizar o formulário.

## Documentação
A API está documentada e disponível para testes em:
`http://localhost:8080/swagger-ui/index.html`
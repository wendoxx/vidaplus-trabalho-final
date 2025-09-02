# Sistema de Gestão Hospitalar e de Serviços de Saúde (SGHSS)

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6.x-blueviolet)
![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-informational)
![JWT](https://img.shields.io/badge/Auth-JWT-orange)

## 1. Visão Geral

Este projeto é o back-end de um **Sistema de Gestão Hospitalar e de Serviços de Saúde (SGHSS)** para a instituição VidaPlus. O objetivo é fornecer uma API RESTful segura, escalável e performática para centralizar as operações de hospitais, clínicas e laboratórios, atendendo a três perfis de usuários: **Pacientes**, **Profissionais de Saúde** e **Administradores**.

Este trabalho foi desenvolvido como parte da disciplina de **Projeto Multidisciplinar** da UNINTER.

## 2. Principais Funcionalidades

-   **Autenticação Segura:** Sistema de login via JWT com controle de acesso baseado nos perfis `ADMIN`, `MEDICO` e `PACIENTE`.
-   **Gerenciamento Completo de Cadastros (CRUD):**
    -   Cadastro público de Pacientes.
    -   Gerenciamento de Médicos, Pacientes e Administradores por usuários autorizados.
-   **Sistema de Agendamento:** Endpoints para agendar, consultar, atualizar e cancelar consultas.
-   **Segurança:** Senhas criptografadas com BCrypt, garantindo a conformidade com a LGPD.

## 3. Arquitetura e Tecnologias

A aplicação segue uma arquitetura em camadas (`Controller`, `Service`, `Repository`) para uma clara separação de responsabilidades.

-   **Linguagem:** Java 21
-   **Framework Principal:** Spring Boot
-   **Segurança:** Spring Security
-   **Autenticação:** JSON Web Tokens (JWT) com criptografia assimétrica (RSA)
-   **Persistência de Dados:** Spring Data JPA / Hibernate
-   **Banco de Dados:** PostgreSQL
-   **Gerenciador de Dependências:** Maven

## 4. Como Executar o Projeto

Siga os passos abaixo para configurar e executar a API localmente.

### Pré-requisitos

-   Java (JDK) 21 ou superior
-   Maven 3.8+
-   PostgreSQL 12+
-   Uma ferramenta de teste de API como [Postman](https://www.postman.com/) ou [Insomnia](https://insomnia.rest/).

### Configuração

1.  **Clone o Repositório:**
    ```bash
    git clone [URL_DO_SEU_REPOSITORIO]
    ```
2.  **Configure o Banco de Dados:**
    -   Inicie seu serviço do PostgreSQL.
    -   Crie um novo banco de dados chamado `vidaplus`.

3.  **Configure a Aplicação:**
    -   No arquivo `src/main/resources/application.properties`, ajuste as credenciais do seu banco de dados local:
        ```properties
        spring.datasource.username=[SEU_USUARIO_POSTGRES]
        spring.datasource.password=[SUA_SENHA_POSTGRES]
        ```
    -   **Importante:** A autenticação JWT utiliza um par de chaves pública/privada. Crie um diretório `keys` dentro de `src/main/resources/` e adicione sua chave privada com o nome `private_key.priv` e sua chave pública como `public_key.pub`.

### Executando

1.  Navegue até a pasta raiz do projeto pelo terminal.
2.  Execute o comando Maven:
    ```bash
    mvn spring-boot:run
    ```
3.  A API estará disponível em `http://localhost:8080`. Um usuário `ADMIN` padrão será criado na primeira inicialização para permitir o cadastro de outros usuários.

## 5. Como Usar a API

### Passo 1: Cadastro e Login

-   **Para se cadastrar como paciente**, envie uma requisição `POST` para `/api/v1/auth/registro-paciente` com seus dados.
-   **Para fazer login**, envie uma requisição `POST` para `/api/v1/auth/login` com seu email e senha. A resposta conterá um `token` JWT.

### Passo 2: Requisições Autenticadas

-   Para acessar endpoints protegidos, copie o `token` recebido no login.
-   Em cada nova requisição, adicione o seguinte cabeçalho (Header):
    -   **Chave:** `Authorization`
    -   **Valor:** `Bearer <seu_token_jwt_aqui>`

## 6. Resumo dos Endpoints

| Método | Endpoint | Acesso | Descrição |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/v1/auth/login` | Público | Autentica um usuário e retorna um token. |
| `POST` | `/api/v1/auth/registro-paciente` | Público | Cadastra um novo paciente. |
| `POST` | `/api/v1/auth/registro-medico` | `ADMIN` | Cadastra um novo médico. |
| `POST` | `/api/v1/consulta` | Autenticado | Agenda uma nova consulta. |
| `GET` | `/api/v1/consulta/{id}` | Autenticado | Busca uma consulta por ID. |
| `GET` | `/api/v1/paciente/{id}` | `ADMIN` / Dono | Busca um paciente por ID. |
| `DELETE` | `/api/v1/medico/{id}` | `ADMIN` | Deleta o cadastro de um médico. |

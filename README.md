# 🚀 TaskManager API

API REST para gerenciamento de tarefas desenvolvida com Java e Spring Boot.

O projeto evoluiu de um CRUD simples para uma aplicação com autenticação JWT, controle de acesso por roles, filtros avançados, paginação, documentação Swagger e testes unitários, simulando práticas utilizadas em aplicações backend reais.

Atualmente o projeto encontra-se na **Versão 2.0**.

---

# 🎯 Objetivo

O principal objetivo deste projeto foi praticar conceitos essenciais do desenvolvimento backend moderno utilizando Java e Spring Boot.

Durante o desenvolvimento foram aplicados conceitos de arquitetura em camadas, persistência de dados, segurança, autenticação, documentação de APIs e testes automatizados.

---

# 🛠 Tecnologias Utilizadas

* Java 17
* Spring Boot
* Spring Security
* JWT Authentication
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven
* Bean Validation
* Swagger / OpenAPI
* JUnit 5
* Mockito

---

# ⭐ Destaques da Versão 2.0

* Autenticação JWT
* Controle de acesso por Roles (USER e ADMIN)
* Criptografia de senhas com BCrypt
* PostgreSQL com Spring Data JPA
* Paginação de resultados
* Ordenação dinâmica
* Filtro por status
* Filtro por prioridade
* Busca por título
* Filtros combinados
* Documentação Swagger/OpenAPI
* Testes unitários com JUnit e Mockito
* Arquitetura em camadas

---

# 🏗 Arquitetura

O projeto segue uma arquitetura em camadas:

Controller → DTO → Service → Repository → Database

### Controller

Responsável por receber as requisições HTTP e retornar as respostas da API.

### DTO

Responsável pelo controle dos dados de entrada e saída da aplicação.

### Service

Contém as regras de negócio do sistema.

### Repository

Responsável pela persistência dos dados.

### Database

Banco de dados PostgreSQL.

---

# 📌 Funcionalidades

## Gerenciamento de Tarefas

* Criar tarefas
* Listar tarefas
* Buscar tarefa por ID
* Atualizar tarefas
* Excluir tarefas

## Segurança

* Cadastro de usuários
* Login com JWT
* Controle de acesso por Roles
* Roles USER e ADMIN
* Senhas criptografadas com BCrypt

## Busca e Organização

* Paginação
* Ordenação
* Filtro por status
* Filtro por prioridade
* Busca por título
* Filtros combinados

## Qualidade

* Bean Validation
* Tratamento global de exceções
* Swagger/OpenAPI
* Testes unitários

---

# 🔗 Endpoints Principais

## Autenticação

POST /auth/login

## Usuários

POST /users

GET /users

GET /users/{id}

## Tarefas

GET /tasks

GET /tasks/{id}

POST /tasks

PUT /tasks/{id}

DELETE /tasks/{id}

---

# 📖 Documentação Swagger

Após executar a aplicação localmente:

http://localhost:8080/swagger-ui/index.html

---

# 🚀 Como Executar

## Pré-requisitos

* Java 17
* PostgreSQL
* Maven

## Passos

1. Clonar o repositório
2. Configurar o PostgreSQL
3. Criar o arquivo `application.properties` utilizando o `application-example.properties`
4. Executar a aplicação
5. Acessar o Swagger

---

# 📂 Estrutura do Projeto

src/main/java

* controller
* dto
* exception
* model
* repository
* security
* service
* config

src/test/java

* service

---

# 📚 Principais Aprendizados

Durante o desenvolvimento deste projeto foram praticados:

* Desenvolvimento de APIs REST com Spring Boot
* Arquitetura em camadas
* Persistência de dados com JPA/Hibernate
* Segurança com Spring Security e JWT
* Controle de acesso por Roles
* Testes unitários com JUnit e Mockito
* Documentação de APIs com Swagger
* Boas práticas de organização de código
* Git e GitHub

---

# 📈 Evolução do Projeto

## Versão 1.0

* CRUD completo de tarefas
* PostgreSQL
* DTOs
* Bean Validation
* Exception Handling
* Swagger/OpenAPI

## Versão 2.0

* Spring Security
* JWT Authentication
* Roles USER / ADMIN
* Cadastro de usuários
* Login autenticado
* Paginação
* Ordenação
* Filtro por status
* Filtro por prioridade
* Busca por título
* Filtros combinados
* Testes unitários com JUnit e Mockito

## Versão 3.0 (Planejada)

* Frontend React
* Integração Frontend + Backend
* Docker
* Deploy em nuvem
* CI/CD
* Testes de integração

---

# 👨‍💻 Autor

Carlos Rykelmy

Projeto desenvolvido para estudos de Engenharia de Software e desenvolvimento backend com Java e Spring Boot.

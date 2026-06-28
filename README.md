# TaskManager API

API REST para gerenciamento de tarefas desenvolvida com **Java** e **Spring Boot**.

O projeto evoluiu de um CRUD simples para uma aplicação backend com autenticação JWT, controle de acesso por usuários, segurança, auditoria automática, documentação da API e testes unitários, simulando práticas utilizadas em aplicações corporativas.

**Versão Atual:** **3.0**

---

# Objetivo

O principal objetivo deste projeto foi praticar conceitos fundamentais do desenvolvimento backend moderno utilizando Java e Spring Boot.

Durante o desenvolvimento foram aplicados conceitos de:

* Arquitetura em camadas
* APIs REST
* Persistência de dados
* Segurança
* Autenticação JWT
* Controle de acesso
* Testes unitários
* Documentação de APIs
* Boas práticas de organização de código

---

# Tecnologias Utilizadas

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

# Destaques da Versão 3.0

* Sistema multiusuário
* Cada usuário acessa apenas suas próprias tarefas
* Proteção contra acesso a tarefas de outros usuários
* Autenticação JWT
* Controle de acesso por Roles (USER e ADMIN)
* Senhas criptografadas com BCrypt
* Auditoria automática (`createdAt` e `updatedAt`)
* Identificação do criador da tarefa (`createdBy`)
* Paginação
* Ordenação
* Busca por título
* Filtros por prioridade e status
* Respostas de erro padronizadas (ErrorResponse)
* JWT Secret externalizado para configuração
* Documentação Swagger/OpenAPI
* Testes unitários com JUnit e Mockito

---

# Arquitetura

O projeto segue uma arquitetura em camadas:

Controller → DTO → Service → Repository → Database

## Controller

Responsável por receber as requisições HTTP e retornar as respostas da API.

## DTO

Responsável pelo controle dos dados de entrada e saída da aplicação.

## Service

Contém as regras de negócio do sistema.

## Repository

Responsável pela persistência dos dados.

## Database

Banco de dados PostgreSQL.

---

# Funcionalidades

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
* Cada usuário acessa apenas suas próprias tarefas
* Proteção contra acesso indevido por ID

## Busca e Organização

* Paginação
* Ordenação
* Busca por título
* Filtro por prioridade
* Filtro por status
* Filtros combinados

## Qualidade

* Bean Validation
* Tratamento global de exceções
* ErrorResponse padronizado
* Auditoria automática
* Swagger/OpenAPI
* Testes unitários

---

# Endpoints Principais

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

# Documentação Swagger

Após executar a aplicação localmente:

http://localhost:8080/swagger-ui/index.html

---

# Como Executar

## Pré-requisitos

* Java 17
* PostgreSQL
* Maven

## Passos

1. Clonar o repositório.
2. Configurar o PostgreSQL.
3. Criar o arquivo `application.properties` utilizando o `application-example.properties`.
4. Executar a aplicação.
5. Acessar o Swagger.

---

# Estrutura do Projeto

```
src/main/java

controller
dto
exception
model
repository
security
service
config

src/test/java

service
```

---

# Principais Aprendizados

Durante o desenvolvimento deste projeto foram praticados:

* Desenvolvimento de APIs REST com Spring Boot
* Arquitetura em camadas
* Persistência de dados com JPA/Hibernate
* Relacionamentos entre entidades
* Segurança com Spring Security e JWT
* Controle de acesso por Roles
* Controle de acesso por proprietário da tarefa
* Auditoria automática
* DTO Pattern
* Tratamento global de exceções
* Testes unitários com JUnit e Mockito
* Documentação com Swagger/OpenAPI
* Organização de configurações sensíveis
* Git e GitHub

---

# Evolução do Projeto

## Versão 1.0

* CRUD de tarefas
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
* Busca por título
* Filtros
* Testes unitários

## Versão 3.0

* Sistema multiusuário
* Cada usuário acessa apenas suas tarefas
* Proteção contra acesso a tarefas de outros usuários
* Auditoria automática (`createdAt` e `updatedAt`)
* Identificação do criador da tarefa (`createdBy`)
* ErrorResponse padronizado
* JWT Secret externalizado
* Atualização dos testes unitários

## Versão 4.0 (Planejada)

* Docker
* Docker Compose
* Flyway
* Deploy da API
* Documentação final do projeto

---

# Autor

**Carlos Rykelmy**

Projeto desenvolvido para estudos de Engenharia de Software e desenvolvimento backend com Java e Spring Boot.

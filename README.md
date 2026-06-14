# TaskManager API

## Sobre o Projeto

TaskManager API é uma API REST desenvolvida em Java com Spring Boot para gerenciamento de tarefas.

O objetivo do projeto foi praticar conceitos fundamentais de desenvolvimento backend, arquitetura em camadas, persistência de dados, validação de dados e construção de APIs REST.

Esta é a versão 1.0 do projeto.

---

## Tecnologias Utilizadas

* Java 17
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven
* Bean Validation
* Swagger/OpenAPI
* JUnit 5

---

## Arquitetura

O projeto segue uma arquitetura em camadas:

Controller → DTO → Service → Repository → Database

### Camadas

**Controller**

* Recebe requisições HTTP
* Retorna respostas da API

**DTO**

* Controla os dados de entrada e saída
* Evita exposição direta das entidades

**Service**

* Contém as regras de negócio

**Repository**

* Responsável pela persistência dos dados

**Database**

* PostgreSQL

---

## Funcionalidades

* Criar tarefas
* Listar tarefas
* Buscar tarefa por ID
* Atualizar tarefas
* Excluir tarefas
* Validação de dados
* Tratamento global de exceções
* Documentação automática com Swagger

---

## Endpoints

### Listar tarefas

GET /tasks

### Buscar tarefa por ID

GET /tasks/{id}

### Criar tarefa

POST /tasks

Exemplo:

{
"title": "Estudar Spring Boot",
"priority": "HIGH"
}

### Atualizar tarefa

PUT /tasks/{id}

### Excluir tarefa

DELETE /tasks/{id}

---

## Documentação Swagger

Após executar a aplicação localmente:

http://localhost:8080/swagger-ui/index.html

---

## Como Executar

### Pré-requisitos

* Java 17
* PostgreSQL
* Maven

### Passos

1. Clonar o repositório

2. Configurar o PostgreSQL

3. Configurar o application.properties

4. Executar a aplicação

5. Acessar o Swagger

---

## Estrutura do Projeto

src/main/java

* controller
* dto
* exception
* model
* repository
* service

---

## Conceitos Aplicados

* Programação Orientada a Objetos
* APIs REST
* Arquitetura em Camadas
* DTO Pattern
* Repository Pattern
* Bean Validation
* Exception Handling
* Persistência com JPA/Hibernate

---

## Roadmap

### Versão 1.0

* CRUD completo
* PostgreSQL
* DTOs
* Bean Validation
* Exception Handling
* Swagger/OpenAPI

### Versão 2.0 (Planejada)

* Interface React
* Integração Frontend + Backend
* Spring Security
* JWT Authentication
* Docker
* Deploy em nuvem
* Testes automatizados avançados

---

## Autor

Carlos Rykelmy

Projeto desenvolvido para estudos de Engenharia de Software e desenvolvimento backend com Java e Spring Boot.

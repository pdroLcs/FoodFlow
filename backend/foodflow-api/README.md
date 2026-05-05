# FoodFlow API

API REST para gerenciamento de cardápio, pedidos e mesas em um ambiente de restaurante.

## Tecnologias utilizadas

- **Java 21**
- **Spring Boot 3**
- **Spring Data JPA / Hibernate**
- **Flyway** (versionamento de banco)
- **PostgreSQL**
- **Maven Wrapper** (`mvnw`)
- **Swagger / OpenAPI** para documentação da API

---

## Estrutura e domínio atual

A modelagem principal da API contempla:

- `Category` (categorias de produtos)
- `Product` (produtos do cardápio)
- `Order` (pedido)
- `OrderItem` (itens do pedido)
- `OrderStatus` (status e transições de pedido)
- `RestaurantTable` (mesas)

As migrations ficam em:

- `src/main/resources/db/migration`

## Arquitetura

A API segue uma arquitetura em camadas:

- Controllers (camada de entrada)
- Services (regras de negócio)
- Repositories (acesso a dados)
- DTOs (contratos da API)
- Mappers (MapStruct)

---

## Variáveis de ambiente (`.env`)

A aplicação lê as variáveis abaixo via `application.properties`:

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`

Copie o arquivo `.env.example` para `.env` e coloque suas variáveis de ambiente:

```env
DB_URL=jdbc:postgresql://localhost:5432/foodflow
DB_USERNAME=foodflow
DB_PASSWORD=foodflow123
```

> Em execução local, você pode exportar essas variáveis no terminal ou usar sua IDE para injetá-las.

---

## Como rodar localmente

### 1) Pré-requisitos

- Java 21 instalado
- PostgreSQL rodando

### 2) Criar banco

No PostgreSQL, crie o banco (exemplo):

```sql
CREATE DATABASE foodflow;
```

### 3) Configurar variáveis de ambiente

Use o `.env` acima como referência.

### 4) Subir aplicação

No diretório `backend/foodflow-api`:

```bash
bash ./mvnw spring-boot:run
```

A API ficará disponível em:

- `http://localhost:8080`

---

## Como rodar com Docker

A aplicação pode ser executada via Docker utilizando o `docker-compose.yml` presente no projeto.

### 1) Subir containers

```bash
docker compose up --build -d
```
> Certifique-se de que o Docker está em execução

### 2) Acompanhar logs

```bash
docker compose logs -f api
```

### 3) Parar ambiente

```bash
docker compose down
```

---

## Documentação da API (Swagger)

Acesse a documentação em:
- `http://localhost:8080/swagger-ui/index.html`
- `http://localhost:8080/v3/api-docs`

---

## Principais endpoints (visão de produto)

> A referência exata (campos, respostas e códigos HTTP) é o Swagger.

### Produtos

- `GET /produtos`
- `GET /produtos/{id}`
- `POST /produtos`
- `PATCH /produtos/{id}`

### Categorias

- `GET /categorias`
- `POST /categorias`
- `DELETE /categorias/{id}`

### Pedidos

- `GET /pedidos`
- `GET /pedidos/{id}`
- `POST /pedidos`
- `PATCH /pedidos/{id}/finalizar`

---

## Regras de negócio

1. **Pedido sempre inicia como `PENDING`**.
2. **Fluxo de status é sequencial e sem retorno**:
    - `PENDING -> READY`
3. **Valor total do pedido é calculado no backend** com base em quantidade e preço unitário dos itens.
4. **Um item de pedido referencia obrigatoriamente** um pedido e um produto.
5. **Produtos podem ser categorizados** e a relação produto-categoria é mantida por chave estrangeira.
6. **Produtos inativos** não aparecem no cardápio.
7. **Uma mesa é considerada ocupada quando possui um pedido com status `PENDING`.**
8. **O estado da mesa é derivado automaticamente e não persistido diretamente.**

---

## Observações

- O projeto usa Flyway para garantir criação/evolução de schema com histórico.
- Em ambientes CI/CD, mantenha as mesmas variáveis de ambiente (`DB_URL`, `DB_USERNAME`, `DB_PASSWORD`).
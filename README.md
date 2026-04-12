# FoodFlow - Sistema de Lanchonete Fullstack

Sistema fullstack para gerenciamento de pedidos de uma lanchonete, permitindo que clientes visualizem o cardápio e realizem pedidos, enquanto administradores gerenciam produtos e acompanham os pedidos.

---

## Tecnologias utilizadas

### Backend
- Java
- Spring Boot
- Spring Data JPA
- Spring Security (JWT)
- PostgreSQL
- Maven

### Frontend
- React
- JavaScript / TypeScript
- Axios
- CSS / Tailwind (opcional)

### DevOps
- Docker
- Docker Compose

---

## Funcionalidades

### Cliente
- Visualizar cardápio
- Filtrar produtos por categoria
- Adicionar itens ao carrinho
- Realizar pedidos
- Acompanhar status do pedido

### Admin
- Criar, editar e inativar produtos
- Upload de imagens dos produtos
- Gerenciar categorias
- Visualizar pedidos
- Atualizar status dos pedidos

---

## Regras de negócio

- Pedido deve ter pelo menos 1 item
- Valor total é calculado no backend
- Produtos inativos não aparecem no cardápio
- Fluxo de status do pedido: `PENDENTE → EM_PREPARO → PRONTO → ENTREGUE`
- Não é permitido pular ou voltar status
---

## Endpoints principais

### Produtos
- `POST /produtos` → Criar produto (admin)
- `GET /produtos` → Listar produtos
- `GET /produtos/{id}` → Buscar produto
- `PUT /produtos/{id}` → Atualizar produto
- `DELETE /produtos/{id}` → Inativar produto

### Categorias
- `GET /categorias`
- `POST /categorias`

### Pedidos
- `POST /pedidos` → Criar pedido
- `GET /pedidos/{id}` → Buscar pedido
- `GET /pedidos` → Listar pedidos (admin)
- `PATCH /pedidos/{id}/status` → Atualizar status

### Autenticação
- `POST /auth/login` → Login (JWT)

## Upload de imagens

- Imagens são enviadas via `MultipartFile`
- Armazenadas localmente em `/uploads`
- Apenas o caminho da imagem é salvo no banco

---

## Como rodar o projeto

### Clonar repositório
```bash
git clone https://github.com/pdroLcs/FoodFlow.git
cd foodflow
```

### Rodar Backend
```bash
cd backend
./mvnw spring-boot:run
```

### Rodar Frontend
```bash
cd frontend
npm install
npm run dev
```

---

### Licença
Esse projeto é de uso educacional e para portfólio.
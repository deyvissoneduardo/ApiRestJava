# ApiRestJava - CRUD de Usuário com JWT

API REST construída com Quarkus contendo CRUD completo de usuário e autenticação JWT.

## Arquitetura do Projeto

O projeto segue arquitetura em camadas com separação clara de responsabilidades:

```
org.apirest/
├── config/          - Configurações (JWT, beans)
├── core/            - Constantes, utilitários, exceções base
│   └── exception/   - Exceções e handler global
├── controller/      - Endpoints REST
├── domain/          - Entidades JPA e DTOs
│   ├── entity/      - Entidades de domínio
│   └── dto/         - Objetos de transferência
├── interfaces/      - Contratos (repositórios, serviços)
├── repositories/    - Implementação de acesso a dados
└── services/        - Regras de negócio
```

## Padrões Utilizados

- **Repository**: abstração do acesso a dados
- **Service**: regras de negócio isoladas
- **DTO**: separação entre modelo de domínio e API

## Como Executar

### 1. Subir o banco de dados

```bash
docker compose up -d
```

Isso sobe PostgreSQL na porta 5432 e pgAdmin na porta 5050.

### 2. Executar a aplicação

```bash
./mvnw quarkus:dev
```

Aplicação disponível em `http://localhost:8080`

## Endpoints Disponíveis

| Método | Endpoint | Autenticação | Descrição |
|--------|----------|--------------|-----------|
| POST | /api/users | Não | Criar usuário |
| GET | /api/users | Sim (JWT) | Listar todos os usuários |
| GET | /api/users/{id} | Sim (JWT) | Buscar usuário por ID |
| PUT | /api/users/{id} | Sim (JWT) | Atualizar usuário |
| DELETE | /api/users/{id} | Sim (JWT) | Excluir usuário |
| POST | /api/auth/login | Não | Login e obter token JWT |

### Exemplo de uso

**Criar usuário:**
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"João Silva","email":"joao@email.com","password":"123456"}'
```

**Login:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"joao@email.com","password":"123456"}'
```

**Acessar rota protegida (com token):**
```bash
curl -X GET http://localhost:8080/api/users \
  -H "Authorization: Bearer SEU_TOKEN_JWT"
```

## Documentação Swagger

Swagger UI disponível em: `http://localhost:8080/q/swagger-ui`

## pgAdmin

- URL: http://localhost:5050
- Email: admin@admin.com
- Senha: admin
- Conexão PostgreSQL: host=postgres, port=5432, db=apirest, user=postgres, password=postgres

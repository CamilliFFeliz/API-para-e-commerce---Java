
# API E-commerce (Spring Boot + MySQL)

## Como rodar localmente

Pré-requisitos: Java 17, Maven e MySQL (ou Docker se preferir).

### Usando Maven localmente
1. Configure `src/main/resources/application.properties` com usuário/senha do MySQL.
2. Crie o banco `ecommerce_db` no seu MySQL.
3. Rode: `mvn clean package` e depois `mvn spring-boot:run` ou `java -jar target/ecommerce-0.0.1-SNAPSHOT.jar`

### Usando Docker
`docker-compose up --build` — irá subir MySQL e a aplicação (conectar via host `db`).

Endpoints principais:
- POST /auth/register
- POST /auth/login
- GET /api/products
- POST /api/orders (Bearer token)

Observações:
- Troque `jwt.secret` por um segredo forte em produção.
- Este projeto é scaffold com implementações básicas para referência.
